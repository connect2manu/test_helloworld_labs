package org.hibernate.ce.auction.model;

import org.hibernate.ce.auction.exceptions.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * An item for sale.
 *
 * @author Christian Bauer <christian@hibernate.org>
 *
 */
@NamedQueries({
    @NamedQuery(
        name = org.hibernate.ce.auction.dao.ItemDAO.QUERY_MINBID,
        queryString="select b from Bid b where b.amount.value = (select min(b.amount.value) from Bid b where b.item.id = :itemid)"
    ),
    @NamedQuery(
        name = org.hibernate.ce.auction.dao.ItemDAO.QUERY_MAXBID,
        queryString="select b from Bid b where b.amount.value = (select max(b.amount.value) from Bid b where b.item.id = :itemid)"
    )
})
@Entity(access = AccessType.FIELD)
@Table(name = "ITEM")
/* TODO: ANN-158
@SecondaryTable(name = "ITEM_BUYER",
                join = { @JoinColumn(name="ITEM_ID") }
               )
*/
public class Item implements Serializable, Comparable, Auditable {

    @Id(generate = GeneratorType.AUTO)
    @Column(name = "USER_ID")
    private Long id = null;

    @Version
    private int version = 0;

    @Column(length = 255, nullable = false, updatable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="SELLER_ID", nullable = false, updatable = false)
    private User seller;

    /* TODO: Try again when ANN-97 is fixed
    @ManyToOne
    @JoinColumn(name="SELLER_NR", referencedColumnName = "CUSTOMER_NR")
    private User seller;
    */

    @Column(length = 4000, nullable = false)
    private String description;

    @org.hibernate.annotations.Type(type = "monetary_amount_usd")
    @org.hibernate.annotations.Columns(columns = {
        @Column( name="INITIAL_PRICE"),
        @Column( name="INITIAL_PRICE_CURRENCY", length = 2)
    })
    private MonetaryAmount initialPrice;

    @org.hibernate.annotations.Type(type = "monetary_amount_usd")
    @org.hibernate.annotations.Columns(columns = {
        @Column( name="RESERVE_PRICE"),
        @Column( name="RESERVE_PRICE_CURRENCY", length = 2)
    })
    private MonetaryAmount reservePrice;

    @Basic(temporalType = TemporalType.TIMESTAMP, optional = false)
    @Column(nullable = false, updatable = false)
    private Date startDate;

    @Basic(temporalType = TemporalType.TIMESTAMP, optional = false)
    @Column(nullable = false, updatable = false)
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "item")
    @org.hibernate.annotations.Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<CategorizedItem> categorizedItems = new HashSet<CategorizedItem>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    @org.hibernate.annotations.IndexColumn(name = "BID_POSITION")
    private List<Bid> bids = new ArrayList<Bid>();

    @ManyToOne
    @JoinColumn(name = "SUCCESSFUL_BID_ID", nullable = true)
    private Bid successfulBid;

    //TODO: Map this using idbag and collection of value types
    /*
    <idbag  name="images"
            table="ITEM_IMAGES">
        <collection-id column="ITEM_IMAGE_ID" type="int">
            <!-- For PostgreSQL, Oracle, HSQLDB, etc. -->
            <generator class="sequence"/>
            <!-- For MS SQL, DB2, etc.
            <generator class="identity"/>
            -->
        </collection-id>
        <key column="ITEM_ID"/>
        <element column="FILENAME" type="string"/>
    </idbag>
    */
    @Transient
    private Collection<String> images = new ArrayList<String>();

    @org.hibernate.annotations.Type(type = "item_state")
    @Column(name = "ITEM_STATE", nullable = false)
    private ItemState state;

    @ManyToOne
    @JoinColumn(name = "APPROVED_BY_USER_ID", nullable = true)
    private User approvedBy;

    @Column( nullable = true)
    private Date approvalDatetime;

    /* TODO: ANN-158
    @ManyToOne
    @JoinColumn(secondaryTable = "ITEM_BUYER", name = "USER_ID")
    */
    @Transient
    private User buyer;

    @OneToMany
    @MapKey(name="id")
    private Map<Long,Bid> bidsByIdentifier = new HashMap<Long,Bid>();

    @Column( nullable = false, updatable = false)
    private Date created = new Date();

    /**
     * No-arg constructor for JavaBean tools.
     */
    Item() {}

    /**
     * Full constructor.
     */
    public Item(String name, String description, User seller,
                MonetaryAmount initialPrice, MonetaryAmount reservePrice,
                Date startDate, Date endDate,
                Set<CategorizedItem> categories, List<Bid> bids, Bid successfulBid, Collection<String> images) {
        this.name = name;
        this.seller = seller;
        this.description = description;
        this.initialPrice = initialPrice;
        this.reservePrice = reservePrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categorizedItems = categories;
        this.bids = bids;
        this.successfulBid = successfulBid;
        this.images = images;
        this.state = ItemState.DRAFT;
    }

    /**
     * Simple properties only constructor.
     */
    public Item(String name, String description, User seller,
                MonetaryAmount initialPrice, MonetaryAmount reservePrice,
                Date startDate, Date endDate) {
        this.name = name;
        this.seller = seller;
        this.description = description;
        this.initialPrice = initialPrice;
        this.reservePrice = reservePrice;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = ItemState.DRAFT;
    }

    // ********************** Accessor Methods ********************** //

    public Long getId() { return id; }
    public int getVersion() { return version; }

    public String getName() { return name; }
    public User getSeller() { return seller; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public MonetaryAmount getInitialPrice() { return initialPrice; }

    public MonetaryAmount getReservePrice() { return reservePrice; }

    public Date getStartDate() { return startDate; }

    public Date getEndDate() { return endDate; }

    public Set<CategorizedItem> getCategorizedItems() { return categorizedItems; }
    public void addCategorizedItem(CategorizedItem catItem) {
        if (catItem == null)
            throw new IllegalArgumentException("Can't add a null CategorizedItem.");
        this.getCategorizedItems().add(catItem);
    }

    public Collection<Bid> getBids() { return bids; }
    public void addBid(Bid bid) {
        if (bid == null)
            throw new IllegalArgumentException("Can't add a null Bid.");
        this.getBids().add(bid);
        // Don't have to set the "other" side, a Bid can only be instantiated with an item given
    }

    public Bid getSuccessfulBid() { return successfulBid; }
    public void setSuccessfulBid(Bid successfulBid) {
        // Has to preserve the integrity by using a procedure and loop, bad Java...
        if (successfulBid != null) {
            for (Bid bid : bids)
                bid.setSuccessful(false);
            successfulBid.setSuccessful(true);
            this.successfulBid = successfulBid;
        }
    }

    public Collection<String> getImages() { return images; }
    public void setImages(Collection<String> images) { this.images = images; }

    public ItemState getState() { return state; }

    public User getApprovedBy() { return approvedBy; }
    public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }

    public Date getApprovalDatetime() { return approvalDatetime; }
    public void setApprovalDatetime(Date approvalDatetime) { this.approvalDatetime = approvalDatetime; }

    public User getBuyer() { return buyer; }
    public void setBuyer(User buyer) { this.buyer = buyer; }

    public Map<Long, Bid> getBidsByIdentifier() { return bidsByIdentifier; }
    public void setBidsByIdentifier(Map<Long, Bid> bidsByIdentifier) { this.bidsByIdentifier = bidsByIdentifier; }

    public Date getCreated() { return created; }

    // ********************** Common Methods ********************** //

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        final Item item = (Item) o;

        if (! (created.getTime() == item.created.getTime()) ) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + created.hashCode();
        return result;
    }

    public String toString() {
        return  "Item ('" + getId() + "'), " +
                "Name: '" + getName() + "' " +
                "Initial Price: '" + getInitialPrice()+ "'";
    }

    public int compareTo(Object o) {
        if (o instanceof Item) {
            return Long.valueOf(this.getCreated().getTime()).compareTo(
                    Long.valueOf( ((Item)o).getCreated().getTime())
                   );
        }
        return 0;
    }

    // ********************** Business Methods ********************** //
    
    /**
     * Places a bid while checking business constraints.
     *
     * This method may throw a BusinessException if one of the requirements
     * for the bid placement wasn't met, e.g. if the auction already ended.
     *
     * @param bidder
     * @param bidAmount
     * @param currentMaxBid  the most valuable bid for this item
     * @param currentMinBid  the least valuable bid for this item
     * @return
     * @throws BusinessException
     */
    public Bid placeBid(User bidder, MonetaryAmount bidAmount,
                        Bid currentMaxBid, Bid currentMinBid)
        throws BusinessException {

        // Check highest bid (can also be a different Strategy (pattern))
        if (currentMaxBid != null && currentMaxBid.getAmount().compareTo(bidAmount) > 0) {
            throw new BusinessException("Bid too low.");
        }

        // Auction is active
        if ( !state.equals(ItemState.ACTIVE) )
            throw new BusinessException("Auction is not active yet.");

        // Auction still valid
        if ( this.getEndDate().before( new Date() ) )
            throw new BusinessException("Can't place new bid, auction already ended.");

        // Create new Bid
        Bid newBid = new Bid(bidAmount, this, bidder);

        // Place bid for this Item
        this.addBid(newBid);

        return newBid;
    }

    /**
     * Anyone can set this item into PENDING state for approval.
     */
    public void setPendingForApproval() {
        state = ItemState.PENDING;
    }

    /**
     * Approve this item for auction and set its state to active.
     *
     * @param byUser
     * @throws BusinessException
     */
    public void approve(User byUser) throws BusinessException {

        if ( !byUser.isAdmin() )
            throw new PermissionException("Not an administrator.");

        if ( !state.equals(ItemState.PENDING) )
            throw new IllegalStateException("Item still in draft.");

        state = ItemState.ACTIVE;
        approvedBy = byUser;
        approvalDatetime = new Date();

    }

}
