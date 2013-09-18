package org.hibernate.ce.auction.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * A single item in a single category, with additional information.
 * <p>
 * This is really a very special mapping. The CategorizedItem class
 * represents an association table. The ER model for this is really
 * a many-to-many association, but instead of two entities and two
 * collections, we mapped this as a single collection (in Category)
 * of composite elements, instances of this class. This simplifies
 * the lifecycle of the association. Navigation is however only
 * possible from Category -> CategorizedItemComponent -> Item, not
 * in the other direction.
 *
 * @see Category
 * @see Item
 * @author Christian Bauer <christian@hibernate.org>
 */
@Embeddable(access = AccessType.FIELD)
public class CategorizedItemComponent implements Serializable, Comparable {

    @Column(name = "ADDED_BY_USER", nullable = false, updatable = false)
	private String username; // This could also be an association to User

    @Basic(temporalType = TemporalType.TIMESTAMP)
    @Column(name = "ADDED_ON", nullable = false, updatable = false)
	private Date dateAdded = new Date();

	@ManyToOne
	@JoinColumn(name = "ITEM_ID", nullable = false, updatable = false)
	private Item item;

    // TODO: What about that?
    @Transient
	private Category category;

	/**
	 * No-arg constructor for JavaBean tools.
	 */
    CategorizedItemComponent() {}

	/**
	 * Full constructor;
	 */
	public CategorizedItemComponent(String username, Category category, Item item) {
		this.username = username;

		this.category = category;
		this.item = item;

	}

	// ********************** Accessor Methods ********************** //

    public String getUsername() { return username; }
    private void setUsername(String username) { this.username = username; }

    public Date getDateAdded() { return dateAdded; }
    private void setDateAdded(Date dateAdded) { this.dateAdded = dateAdded; }

    public Item getItem() { return item; }
    private void setItem(Item item) { this.item = item; }

    public Category getCategory() { return category; }
    private void setCategory(Category category) { this.category = category; }

	// ********************** Common Methods ********************** //

	public int compareTo(Object o) {
		// CategorizedItems are sorted by date
		if (o instanceof CategorizedItemComponent)
			return getDateAdded().compareTo( ((CategorizedItemComponent)o).getDateAdded() );
		return 0;
	}

	public String toString() {
		return  "Added by: '" + getUsername() + "', " +
				"On Date: '" + getDateAdded();
	}

	// ********************** Business Methods ********************** //

}