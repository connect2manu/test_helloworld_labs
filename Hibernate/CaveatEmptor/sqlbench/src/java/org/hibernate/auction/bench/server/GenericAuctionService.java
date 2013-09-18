package org.hibernate.auction.bench.server;

import org.hibernate.auction.persistence.dao.*;
import org.hibernate.auction.model.*;
import org.hibernate.auction.report.*;

import java.util.Collection;

/**
 * A generic implementation of the AuctionService.
 *
 * Note that you can extend this class and override the
 * setUp() and tearDown() methods if you provide the appropriate
 * DAO interface with your own DAOFactoryImpl. If your persistence
 * solution does not provide transactional object states, you might
 * have to implement the AuctionService interface yourself and use
 * your own DAO interfaces.
 */
public abstract class GenericAuctionService implements AuctionService {
    DAOFactory daoFactory;

    // Setup service
    public void setUp(Class daoFactoryImpl) throws Exception {
        daoFactory = DAOFactory.getInstance(daoFactoryImpl);
    }

    // Transaction control
    public abstract void beginTransaction();
    public abstract void endTransaction();

    // Category operations
    public Category findCategoryById(Long categoryId, boolean lockCategory) {
        CategoryDAO catDAO = daoFactory.getCategoryDAO();
        return catDAO.getCategoryById(categoryId, lockCategory);
    }

    public void createCategory(Category cat) {
        CategoryDAO catDAO = daoFactory.getCategoryDAO();
        catDAO.makePersistent(cat);
    }

    public void storeCategory(Category cat) {
        CategoryDAO catDAO = daoFactory.getCategoryDAO();
        catDAO.makePersistent(cat);
    }

    public void deleteCategory(Category cat) {
        CategoryDAO catDAO = daoFactory.getCategoryDAO();
        catDAO.makeTransient(cat);
    }

    public Collection findItemsInCategory(Category cat) {
        CategoryDAO catDAO = daoFactory.getCategoryDAO();
        return catDAO.findItemsInCategory(cat);
    }

    // Item operations
    public Item findItemById(Long itemId, boolean lock) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        return itemDAO.getItemById(itemId, false);
    }

    public Collection findItemsByExample(Item exampleItem) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        return itemDAO.findByExample(exampleItem);
    }

    public void createItem(Item item) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        itemDAO.makePersistent(item);
    }

    public void storeItem(Item item) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        itemDAO.makePersistent(item);
    }

    // Bid operations
    public Bid placeBid(Item item, User user, MonetaryAmount bidAmount, boolean lockItem) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        UserDAO userDAO = daoFactory.getUserDAO();

        // Bring objects into persistence context
        itemDAO.makePersistent(item);
        userDAO.makePersistent(user);

        // Lock item if neccessary
        item = itemDAO.getItemById(item.getId(), lockItem);

        // Get maximum and minimum bids
        Bid maxBid = itemDAO.getMinBid(item.getId());
        Bid minBid = itemDAO.getMinBid(item.getId());

        // Place bid
        return item.placeBid(user, bidAmount, maxBid, minBid);
    }

    public Bid getCurrentMaxBid(Item item) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        return itemDAO.getMaxBid(item.getId());
    }

    public Bid getCurrentMinBid(Item item) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        return itemDAO.getMinBid(item.getId());
    }

    public ItemAvgBidAmount[] reportAverageBids(Collection items) {
        ItemDAO itemDAO = daoFactory.getItemDAO();
        return itemDAO.findAverageBids(items);
    }

    // User operations
    public User findUserById(Long userId, boolean lockUser) {
        UserDAO userDAO = daoFactory.getUserDAO();
        return userDAO.getUserById(userId, lockUser);
    }

    public Collection findUsersByExample(User exampleUser) {
        UserDAO userDAO = daoFactory.getUserDAO();
        return userDAO.findByExample(exampleUser);
    }

    public Collection findUsersWithSelfBid() {
        UserDAO userDAO = daoFactory.getUserDAO();
        return userDAO.findUsersWithSelfBid();
    }

    public void createUser(User user) {
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.makePersistent(user);
    }

    public void storeUser(User user) {
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.makePersistent(user);
    }
}
