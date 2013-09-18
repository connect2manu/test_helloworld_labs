package org.hibernate.auction.model;

import org.hibernate.auction.exceptions.*;

import java.io.Serializable;
import java.util.*;

/**
 * An item for sale.
 *
 * @author Christian Bauer <christian@hibernate.org>
 *
 */
public class Item implements Serializable, Comparable {

	private Long id = null;
	private int version;
	private String name;
	private User seller;
	private String description;
	private MonetaryAmount initialPrice;
	private MonetaryAmount reservePrice;
	private Date startDate;
	private Date endDate;
	private Set categories = new HashSet();
	private Collection bids = new ArrayList();
	private Bid successfulBid;
	private ItemState state;
	private User approvedBy;
	private Date approvalDatetime;
	private Date created;

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
				Set categories, List bids, Bid successfulBid) {
		this.name = name;
		this.seller = seller;
		this.description = description;
		this.initialPrice = initialPrice;
		this.reservePrice = reservePrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.categories = categories;
		this.bids = bids;
		this.successfulBid = successfulBid;
		this.state = ItemState.DRAFT;
		this.created = new Date();
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
		this.created = new Date();
	}

	// ********************** Accessor Methods ********************** //

	public Long getId() { return id; }

	public String getName() { return name; }
	public User getSeller() { return seller; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public MonetaryAmount getInitialPrice() { return initialPrice; }

	public MonetaryAmount getReservePrice() { return reservePrice; }

	public Date getStartDate() { return startDate; }

	public Date getEndDate() { return endDate; }

	public Set getCategories() { return categories; }
	public void addCategory(Category category) {
		if (category == null)
			throw new IllegalArgumentException("Can't add a null Category.");
		if (category.getItems() != null)
			category.getItems().add(this);
		this.getCategories().add(category);
	}

	public Collection getBids() { return bids; }
	public void addBid(Bid bid) {
		if (bid == null)
			throw new IllegalArgumentException("Can't add a null Bid.");
		this.getBids().add(bid);
	}

	public Bid getSuccessfulBid() { return successfulBid; }
	public void setSuccessfulBid(Bid successfulBid) { this.successfulBid = successfulBid; }

	public ItemState getState() { return state; }

	public User getApprovedBy() { return approvedBy; }
	public void setApprovedBy(User approvedBy) { this.approvedBy = approvedBy; }

	public Date getApprovalDatetime() { return approvalDatetime; }
	public void setApprovalDatetime(Date approvalDatetime) { this.approvalDatetime = approvalDatetime; }

    public void setCreated(Date created) { this.created = created; }
	public Date getCreated() { return created; }

	// ********************** Common Methods ********************** //

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Item)) return false;

		final Item item = (Item) o;

		if (!getName().equals(item.getName())) return false;
		if (!getCreated().equals(item.getCreated())) return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = getName().hashCode();
		result = 29 * result + getCreated().hashCode();
		return result;
	}

	public String toString() {
		return  "Item ('" + getId() + "'), " +
				"Name: '" + getName() + "' " +
				"Initial Price: '" + getInitialPrice()+ "'";
	}

	public int compareTo(Object o) {
		if (o instanceof Item) {
			return this.getCreated().compareTo( ((Item)o).getCreated() );
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
