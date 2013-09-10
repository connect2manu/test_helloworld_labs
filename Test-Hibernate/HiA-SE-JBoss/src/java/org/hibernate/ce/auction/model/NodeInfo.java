package org.hibernate.ce.auction.model;

import javax.persistence.*;
import java.util.*;

/**
 * The delegate for all nested set node management.
 * Can be mapped as an immutable component if the
 * delegator is mapping the mutable properties, such
 * as parent and children.
 *
 * @author christian.bauer@jboss.com
 */
@Embeddable(access = AccessType.FIELD)
// TODO: How do I make the whole <component update="false">?
public class NodeInfo {

    // Delegator
    // TODO: How do I map this backpointer?
    @Transient
    private Node node;

    @Transient
    private Node parent;
    @Transient
    private Set<Node> children = new HashSet<Node>();

    @Column(name = "NS_THREAD", updatable = false)
    public long thread;

    @Column(name = "NS_LEFT", updatable = false)
    public long left = 1;

    @Column(name = "NS_RIGHT", updatable = false)
    public long right = 2;

    NodeInfo() {}

    public NodeInfo(Node node) { this.node = node; }

    public long getThread() {
        return thread;
    }

    public void setThread(long thread) {
        this.thread = thread;
    }

    public long getLeft() {
        return left;
    }

    public void setLeft(long left) {
        this.left = left;
    }

    public long getRight() {
        return right;
    }

    public void setRight(long right) {
        this.right = right;
    }

    public Node getParent() { return parent; }
    public void setParent(Node parent) { this.parent = parent; }

    public Set<Node> getChildren() { return children; }
    public void setChildren(Set<Node> children) { this.children = children; }

    public void addChild(Node child) {
        if (child == null)
            throw new IllegalArgumentException("Can't add a null node as child.");

        // Remove from old parent - one-to-many multiplicity
        if (child.getParent() != null)
            child.getParent().getChildren().remove(child);

        // Set parent in child
        child.setParent(node);

        // Set child in parent
        node.getChildren().add(child);
    }
    public void removeChild(Node child) {
        if (child == null) return;
        // Remove from parent and set parent to null
        if (child.getParent() != null)
            child.getParent().getChildren().remove(child);
        child.setParent(null);
    }

    public Node getNode() {
        return node;
    }
    private void setNode(Node node) {
        this.node = node;
    }

    public String toString() {
        return "NodeInfo for " + getNode().getId() + " = (T:" + getThread() + ", L:" + getLeft() + ", R:" + getRight() + ")";
    }

}
