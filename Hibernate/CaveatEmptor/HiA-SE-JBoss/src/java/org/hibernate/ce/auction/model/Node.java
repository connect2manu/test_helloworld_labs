package org.hibernate.ce.auction.model;

import java.util.Set;

/**
 * The delegator interface for a node in a nested set.
 *
 * @author christian.bauer@jboss.com
 */
public interface Node {

    public Long getId();

    public String getDescription();

    public NodeInfo getNodeInfo();

    public Node getParent();
    public void setParent(Node parent);
    public Set<Node> getChildren();
    public void setChildren(Set<Node> children);

    public void addChild(Node child);
    public void removeChild(Node child);
}
