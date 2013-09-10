package org.hibernate.auction.model;

import java.util.Date;
import java.io.Serializable;

/**
 * A single item in a single category, with additional information.
 * <p>
 * This class is optional in CaveatEmptor. It may be used in an
 * alternative many-to-many mapping from Category to Item, see
 * the Category.hbm.xml file for an example. This class is then
 * mapped as a component of Category (in a collection).
 *
 * @see Category
 * @see Item
 * @author Christian Bauer <christian@hibernate.org>
 */
public class CategorizedItem implements Serializable, Comparable {

	private String username; // This could also be an association to User
	private Date dateAdded;
	private Item item;
	private Category category;

	/**
	 * No-arg constructor for JavaBean tools.
	 */
	CategorizedItem() {}

	/**
	 * Full constructor;
	 */
	public CategorizedItem(String username, Item item, Category category) {
		this.username = username;
		this.item = item;
		this.category = category;
		this.dateAdded = new Date();
	}

	// ********************** Accessor Methods ********************** //

	public String getUsername() { return username; }
	public Date getDateAdded() { return dateAdded; }
	public Item getItem() { return item; }
	public Category getCategory() { return category; }

	// ********************** Common Methods ********************** //

	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CategorizedItem)) return false;

		final CategorizedItem categorizedItem = (CategorizedItem) o;

		if (!dateAdded.equals(categorizedItem.dateAdded)) return false;
		if (!getItem().getId().equals(categorizedItem.getItem().getId())) return false;
		if (!getCategory().getId().equals(categorizedItem.getCategory().getId())) return false;
		if (!username.equals(categorizedItem.username)) return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = username.hashCode();
		result = 29 * result + dateAdded.hashCode();
		return result;
	}

	public int compareTo(Object o) {
		// CategorizedItems are sorted by date
		if (o instanceof CategorizedItem)
			return getDateAdded().compareTo( ((CategorizedItem)o).getDateAdded() );
		return 0;
	}

	public String toString() {
		return  "Username: '" + getUsername() + "', " +
				"Date added: '" + getDateAdded() + "', " +
				getItem();
	}

	// ********************** Business Methods ********************** //

}
