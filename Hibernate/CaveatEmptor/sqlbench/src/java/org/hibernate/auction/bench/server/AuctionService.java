package org.hibernate.auction.bench.server;

import org.hibernate.auction.model.*;
import org.hibernate.auction.report.*;
import java.util.Collection;

/**
 * Basic service interface for the SQL test.
 *
 * Implement this interface and specify your implementation class on the commandline.
 * It is recommended that you also hide your persistence logic with a DAO. If you
 * are sure that your DAO provides the correct methods, you can even re-use the
 * HibernateAuctionService implementation and switch to your DAO implementation only.
 *
 * Note that all modification operations (such as storeItem() or deleteCategory())
 * have to implement a version check for optimistick locking!
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public interface AuctionService {

    // Housekeeping
    public void setUp(Class daoFactoryImpl) throws Exception;
    public void tearDown() throws Exception;

    // Transaction control
    public void beginTransaction();
    public void endTransaction();

    /**
     * Finds a particular Category by ID, optionally locks the object.
     *
     * This method should return a single Category instance, the client of this
     * method is allowed to traverse all child categories recursively.
     */
    public Category             findCategoryById(Long categoryId, boolean lockCategory);

    /**
     * Returns a collection of Item instances in a particular Category.
     */
    public Collection           findItemsInCategory(Category category);

    /**
     * Create a persistent Category.
     *
     * Any associated child categories or other objects will not be considered.
     */
    public void                 createCategory(Category cat);

    /**
     * Make modifications to a Category persistent.
     *
     * Any associated child categories or other objects will not be considered.
     */
    public void                 storeCategory(Category cat);

    /**
     * Make a persistent Category transient, that is, delete it.
     *
     * This operation cascades to associated persistent child categories, recursively.
     */
    public void                 deleteCategory(Category cat);

    /**
     * Finds a particular Item by ID, optional locks the object.
     *
     * Note that a client is allowed to traverse the associated bids of an item.
     */
    public Item                 findItemById(Long itemId, boolean lockItem);

    /**
     * Finds Items that match the example.
     *
     * Null properties are ignored, string matching is exact.
     */
    public Collection           findItemsByExample(Item exampleItem);

    /**
     * Create a persistent Item.
     *
     * This operation cascades to all associated bids, that is, all associated bid
     * instances have to be made persistent, too.
     */
    public void                 createItem(Item item);

    /**
     * Make modifications to an Item persistent.
     *
     * This operation cascades to any associated bid instances and stores any
     * modifications. However, bids are considered immutable.
     */
    public void                 storeItem(Item item);

    /**
     * Place a new bid with the given monetary amount on an item.
     *
     * This method can also lock the item with a non-shared pessimistic lock to avoid
     * any concurrent modifications or retrievals of the same item during bid evaluation.
     * This method returns the persistent bid that has been placed in the bids collection
     * of the Item.
     */
    public Bid                  placeBid(Item item, User user, MonetaryAmount bidAmount, boolean lockItem);

    /**
     * Returns the current highest bid for the given Item.
     *
     * Note that this operation has to be performed by the database, not in memory.
     */
    public Bid                  getCurrentMaxBid(Item item);

    /**
     * Returns the current lowest bid for the given Item.
     *
     * Note that this operation has to be performed by the database, not in memory.
     */
    public Bid                  getCurrentMinBid(Item item);

    /**
     * Returns a report object.
     *
     * The ItemAvgBidAmount contains the Item identifier and the average bid for this
     * particular Item.
     */
    public ItemAvgBidAmount[]   reportAverageBids(Collection items);

    /**
     * Finds a particular User by ID, optionally locks the object.
     *
     * Note that the client of this method may traverse the associated addresses and
     * billing details of the returned User.
     */
    public User                 findUserById(Long userId, boolean lockUser);

    /**
     * Finds Users that match the example.
     *
     * Null properties are ignored, string matching is exact.
     */
    public Collection           findUsersByExample(User exampleUser);

    /**
     * Returns all Users that have placed Bids for themselves.
     */
    public Collection           findUsersWithSelfBid();

    /**
     * Create a persistent User.
     *
     * This operation cascades to all addresses and billing details.
     */
    public void                 createUser(User user);


    /**
     * Make modifications to a User persistent.
     *
     * This operation cascades to any associated addresses and billing details.
     */
    public void                 storeUser(User user);
}
