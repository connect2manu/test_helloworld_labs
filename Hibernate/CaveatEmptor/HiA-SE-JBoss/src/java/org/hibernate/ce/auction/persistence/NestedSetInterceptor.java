package org.hibernate.ce.auction.persistence;

import org.hibernate.*;
import org.hibernate.ce.auction.model.*;
import org.hibernate.type.Type;
import org.apache.commons.logging.*;

import java.io.Serializable;
import java.util.*;

public class NestedSetInterceptor extends EmptyInterceptor {

    private static Log log = LogFactory.getLog(NestedSetInterceptor.class);

    private Collection newNodes = new ArrayList();
    private Collection deletedNodes = new ArrayList();

    private Session owningSession;

    public void setOwningSession(Session owningSession) {
        this.owningSession = owningSession;
    }

    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof Node && ((Node) entity).getNodeInfo().getThread() != 0) {
            Node node = (Node) entity;
            Node oldParent = null;
            Node newParent = null;

            // Find index of "parent" property
            int parentPropertyIndex = -1;
            for (int it = 0; it < propertyNames.length; it++) {
                String propertyName = propertyNames[it];
                if (propertyName.equals("parent")) parentPropertyIndex = it;
            }
            // Get old and current state for the "parent" property
            if (previousState != null) oldParent = (Node) previousState[parentPropertyIndex];
            if (currentState != null) newParent = (Node) currentState[parentPropertyIndex];

            // Move the node if parent changed
            if ( oldParent != null && !oldParent.equals(newParent) ) {
                // Delete the node from its current position (possibly also its children)
                log.debug("Node will be deleted: " + node);
                deletedNodes.add(node);
                if (newParent != null) {
                    // Place it in new position
                    log.debug("Node will be inserted: " + node);
                    newNodes.add(node);
                }
            }
        }
        return null;
     }

    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Node && ((Node) entity).getNodeInfo().getThread() != 0) {
            log.debug("Node will be deleted: " + entity);
            deletedNodes.add(entity);
        }
        super.onDelete(entity, id, state, propertyNames, types);
    }

    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        if (entity instanceof Node && ((Node) entity).getNodeInfo().getThread() == 0) {
            log.debug("Node will be inserted: " + entity);
            newNodes.add(entity);
        }
        return false;
    }

    public void postFlush(Iterator entities) throws CallbackException {

        // Get a collection of all nodes in memory for synchronization
        Collection nodesInContext = new HashSet();
        while (entities.hasNext()) {
            Object o = entities.next();
            if (o instanceof Node) {
                nodesInContext.add(o);
            }
        }

        Session tmpSession = owningSession.getSessionFactory()
                .openSession( owningSession.connection() );
        try {

            // Handle delete nodes in tree
            Collection alreadyDeleted = new ArrayList();
            for (Iterator it = deletedNodes.iterator(); it.hasNext();) {
                Node node = (Node) it.next();
                String entityName = tmpSession.getSessionFactory()
                        .getClassMetadata( node.getClass() ).getEntityName();

                if (alreadyDeleted.contains(node)) continue;

                // Node with children, deleting subtree
                log.debug("Deleting node: " + node);

                int moveOffset = 2;

                if (node.getNodeInfo().getRight() != (node.getNodeInfo().getLeft() + 1) ) {
                    // Node with children, deleting subtree
                    log.debug("Deleting subtree of node: " + node);

                    // Calculate update offset for other nodes
                    moveOffset = (int)Math.floor( (node.getNodeInfo().getRight() -
                                                       node.getNodeInfo().getLeft()) / 2 );
                    moveOffset = 2 * (1 + moveOffset);

                    // Add subtree nodes to already deleted collection, avoid duplicate updates
                    for (Iterator subit = deletedNodes.iterator(); subit.hasNext();) {
                        Node n = (Node) subit.next();
                        if (n.getNodeInfo().getThread() == node.getNodeInfo().getThread()
                            && n.getNodeInfo().getLeft() > node.getNodeInfo().getLeft()
                            && n.getNodeInfo().getLeft() < node.getNodeInfo().getRight() ) {
                            log.debug("Subtree node, mark as already deleted: " + n);
                            alreadyDeleted.add(n);
                        }
                    }
                }

                Query updateLeft = tmpSession
                        .createQuery("update " + entityName + " n set n.nodeInfo.left = n.nodeInfo.left - :offset " +
                                     "where n.nodeInfo.thread = :thread and n.nodeInfo.left > :right");
                updateLeft.setParameter("offset", moveOffset);
                updateLeft.setParameter("thread", node.getNodeInfo().getThread());
                updateLeft.setParameter("right", node.getNodeInfo().getRight());
                updateLeft.executeUpdate();
                for (Iterator itContext = nodesInContext.iterator(); itContext.hasNext();) {
                    Node n = (Node) itContext.next();
                    if (n.getNodeInfo().getThread() == node.getNodeInfo().getThread()
                        && n.getNodeInfo().getLeft() > node.getNodeInfo().getRight()) {
                        n.getNodeInfo().setLeft(n.getNodeInfo().getLeft() - moveOffset);
                        log.debug("Updated node in memory: " + n);
                    }
                }

                Query updateRight = tmpSession
                        .createQuery("update " + entityName + " n set n.nodeInfo.right = n.nodeInfo.right - :offset " +
                                     "where n.nodeInfo.thread = :thread and n.nodeInfo.right > :right");
                updateRight.setParameter("offset", moveOffset);
                updateRight.setParameter("thread", node.getNodeInfo().getThread());
                updateRight.setParameter("right", node.getNodeInfo().getRight());
                updateRight.executeUpdate();
                for (Iterator itContext = nodesInContext.iterator(); itContext.hasNext();) {
                    Node n = (Node) itContext.next();
                    if (n.getNodeInfo().getThread() == node.getNodeInfo().getThread()
                        && n.getNodeInfo().getRight() > node.getNodeInfo().getRight()) {
                        n.getNodeInfo().setRight(n.getNodeInfo().getRight() - moveOffset);
                        log.debug("Updated node in memory: " + n);
                    }
                }
            }


            // Handle new nodes in tree
            for (Iterator it = newNodes.iterator(); it.hasNext();) {
                Node node = (Node) it.next();
                String entityName = tmpSession.getSessionFactory()
                        .getClassMetadata( node.getClass() ).getEntityName();

                if (node.getNodeInfo().getParent() != null) {

                    // New child node
                    log.debug("New child node: " + node);
                    long parentThread = node.getParent().getNodeInfo().getThread();
                    long parentRight = node.getParent().getNodeInfo().getRight();

                    Query updateLeft = tmpSession
                            .createQuery("update " + entityName + " n set n.nodeInfo.left = n.nodeInfo.left + 2 " +
                                         "where n.nodeInfo.thread = :thread and n.nodeInfo.left > :right");
                    updateLeft.setParameter("thread", parentThread);
                    updateLeft.setParameter("right", parentRight);
                    updateLeft.executeUpdate();
                    for (Iterator itContext = nodesInContext.iterator(); itContext.hasNext();) {
                        Node n = (Node) itContext.next();
                        if (n.getNodeInfo().getThread() == parentThread && n.getNodeInfo().getLeft() > parentRight) {
                            n.getNodeInfo().setLeft(n.getNodeInfo().getLeft() + 2);
                            log.debug("Updated node in memory: " + n);
                        }
                    }

                    Query updateRight = tmpSession
                            .createQuery("update " + entityName + " n set n.nodeInfo.right = n.nodeInfo.right + 2 " +
                                         "where n.nodeInfo.thread = :thread and n.nodeInfo.right >= :right");
                    updateRight.setParameter("thread", parentThread);
                    updateRight.setParameter("right", parentRight);
                    updateRight.executeUpdate();
                    for (Iterator itContext = nodesInContext.iterator(); itContext.hasNext();) {
                        Node n = (Node) itContext.next();
                        if (n.getNodeInfo().getThread() == parentThread && n.getNodeInfo().getRight() >= parentRight) {
                            n.getNodeInfo().setRight(n.getNodeInfo().getRight() + 2);
                            log.debug("Updated node in memory: " + n);
                        }
                    }

                    Query updateNode = tmpSession
                            .createQuery("update " + entityName + " n set n.nodeInfo.thread = :thread, " +
                                         "n.nodeInfo.left = :left, " +
                                         "n.nodeInfo.right = :right " +
                                         "where n.id = :nid");
                    updateNode.setParameter("thread", parentThread);
                    updateNode.setParameter("left", parentRight);
                    updateNode.setParameter("right", parentRight + 1);
                    updateNode.setParameter("nid", node.getId());
                    updateNode.executeUpdate();

                    node.getNodeInfo().setThread(parentThread);
                    node.getNodeInfo().setLeft(parentRight);
                    node.getNodeInfo().setRight(parentRight + 1);
                    log.debug("Inserted node: " + node);

                } else {
                    // New root node, hence new thread (thread identifier is root node identifier)
                    log.debug("New root node: " + node);
                    node.getNodeInfo().setThread(node.getId());

                    // Set thread in database
                    Query updateInDB =
                            tmpSession.createQuery("update " + entityName + " n set n.nodeInfo.thread = :thread " +
                                                   "where n.id = :nid");
                    updateInDB.setParameter("thread", node.getId());
                    updateInDB.setParameter("nid", node.getId());
                    updateInDB.executeUpdate();
                    log.debug("Inserted node: " + node);
                }
            }

        } finally {
            tmpSession.close();
            newNodes.clear();
            deletedNodes.clear();
        }
    }
}
