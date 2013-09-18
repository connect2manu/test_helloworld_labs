package org.hibernate.ce.auction.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * The CaveatEmptor Category can have child categories and each has Items.
 * <p>
 * Categories can be nested, this is expressed as a bidirectional one-to-many
 * relationship that references parent and child categories. Each Category
 * can have many Items (and an Item can be in many categories). This is a
 * true many-to-many relationship. There are two options how you can use it.
 * <p>
 * Frist, the collection <tt>categorizedItems</tt> is a one-to-many association
 * to an entity class <tt>CategorizedItem</tt> that represents the link. The
 * <tt>Item</tt> class has the same collection mapped, to make it bidirectional.
 * <p>
 * Second, the collection <tt>categorizedItemComponents</tt> is a collection of
 * value typed elements, of value type <tt>CategorizedItemComponent</tt>. This
 * simplifies management of the link (no intermediate entity class) but allows
 * only unidirectional navigation. The <tt>Item</tt> class does not know anything
 * about this collection or the components - no shared references.
 * <p>
 * This class is not safe for usage in collections when detached - it does
 * not override equals/hashCode!
 *
 * @see Item
 * @see CategorizedItem
 * @see CategorizedItemComponent
 * @author Christian Bauer <christian@hibernate.org>
 */
@Entity(access = AccessType.PROPERTY) // Can't use FIELD, have delegate calls to NodeInfo
@Table(name = "CATEGORY",
       uniqueConstraints =
        {@UniqueConstraint(columnNames =
            {"CAT_NAME", "PARENT_CATEGORY_ID"} )
        }
    )
public class Category implements Node, Serializable, Comparable {

    private int version = 0;
    private String name;
    private List<Item> items = new ArrayList<Item>();
    private Set<CategorizedItem> categorizedItems = new HashSet<CategorizedItem>();
    private Set<CategorizedItemComponent> categorizedItemComponents = new HashSet<CategorizedItemComponent>();
    private Map<Item,User> itemsAndUser = new HashMap<Item,User>();
    private Date created = new Date();

    /**
     * No-arg constructor for JavaBean tools.
     */
    Category() {}

    /**
     * Full constructor.
     */
    public Category(String name,
                    List<Item> items,
                    Set<CategorizedItem> categorizedItems,
                    Set<CategorizedItemComponent> categorizedItemComponents,
                    Map<Item,User> itemsByUser) {
        this.name = name;
        this.items = items;
        this.categorizedItems = categorizedItems;
        this.categorizedItemComponents = categorizedItemComponents;
        this.itemsAndUser = itemsByUser;
    }

    /**
     * Simple constructor.
     */
    public Category(String name) {
        this.name = name;
    }

    // ********************** Accessor Methods ********************** //

    @Version
    public int getVersion() { return version; }
    private void setVersion(int version) { this.version = version; }

    @Column(name = "CAT_NAME", length = 255, nullable = false)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @ManyToMany
    @JoinTable(
        table = @Table(name = "CATEGORY_ITEM"),
        joinColumns = {@JoinColumn(name = "CATEGORY_ID")},
        inverseJoinColumns = {@JoinColumn(name = "ITEM_ID")}
    )
    @org.hibernate.annotations.IndexColumn(name = "DISPLAY_POSITION")
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public Set<CategorizedItem> getCategorizedItems() { return categorizedItems; }
    private void setCategorizedItems(Set<CategorizedItem> categorizedItems) { this.categorizedItems = categorizedItems; }
    public void addCategorizedItem(CategorizedItem catItem) {
        if (catItem == null)
            throw new IllegalArgumentException("Can't add a null CategorizedItem.");
        this.getCategorizedItems().add(catItem);
    }

    // TODO: http://opensource2.atlassian.com/projects/hibernate/browse/ANN-138
    @Transient
    public Set<CategorizedItemComponent> getCategorizedItemComponents() { return categorizedItemComponents; }
    private void setCategorizedItemComponents(Set<CategorizedItemComponent> categorizedItemComponents) { this.categorizedItemComponents = categorizedItemComponents; }

    // Not supported with annotations...
    @Transient
    public Map<Item, User> getItemsAndUser() { return itemsAndUser; }
    public void setItemsAndUser(Map<Item, User> itemsAndUser) { this.itemsAndUser = itemsAndUser; }

    @Column( nullable = false, updatable = false)
    public Date getCreated() { return created; }
    private void setCreated(Date created) { this.created = created; }

    // ********************** Common Methods ********************** //

    // TODO: Implement equals and hashcode

    public int compareTo(Object o) {
        if (o instanceof Category) {
            return this.getName().compareTo( ((Category)o).getName() );
        }
        return 0;
    }

    // ********************** Node ********************** //

    private Long id = null;
    private NodeInfo nodeInfo = new NodeInfo(this);

    @Id(generate = GeneratorType.AUTO)
    @Column(name = "CAT_ID")
    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    // Use the delegate for nested set management, override here if needed
    @Embedded
    @AttributeOverrides( {
        @AttributeOverride(name = "thread", column = @Column(name = "CAT_THREAD_ID") ),
        @AttributeOverride(name = "left", column = @Column(name = "CAT_LEFT") ),
        @AttributeOverride(name = "right", column = @Column(name = "CAT_RIGHT") )
    })
    public NodeInfo getNodeInfo() { return nodeInfo; }
    private void setNodeInfo(NodeInfo nodeInfo) { this.nodeInfo = nodeInfo; }

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, targetEntity = Category.class)
    @org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "PARENT_CATEGORY_ID", nullable = true)
    public Node getParent() { return nodeInfo.getParent(); }
    public void setParent(Node parent) { nodeInfo.setParent(parent); }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", targetEntity = Category.class)
    public Set<Node> getChildren() { return nodeInfo.getChildren(); }
    public void setChildren(Set<Node> children) { nodeInfo.setChildren(children); };

    public void addChild(Node child) { nodeInfo.addChild(child); }
    public void removeChild(Node child) { nodeInfo.removeChild(child); }

    @Transient
    public String getDescription() {
        return getName();
    }

    private List childCategories = new ArrayList();

    @Transient
    public List getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List childCategories) {
        this.childCategories = childCategories;
    }

    public String toString() {
        return  "Name: '" + getName() + "' " + getNodeInfo();
    }

    // ********************** Legacy example ********************** //

    private Map<Long,Item> itemsByIdentifier = new HashMap<Long,Item>();
    /* Not supported with annotations... */
    @Transient
    public Map<Long, Item> getItemsByIdentifier() { return itemsByIdentifier; }
    public void setItemsByIdentifier(Map<Long, Item> itemsByIdentifier) { this.itemsByIdentifier = itemsByIdentifier; }

    // ********************** Business Methods ********************** //

}
