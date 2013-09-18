package org.hibernate.auction.bench.client;

import org.hibernate.auction.bench.server.AuctionService;
import org.hibernate.auction.model.*;
import org.hibernate.auction.report.ItemAvgBidAmount;
import org.apache.commons.logging.*;

import java.util.*;
import java.math.BigDecimal;

/**
 * The client for the SQL test.
 *
 * This class can be directly started, it needs two startup arguments: The classname
 * of the AuctionService implementation and the name of the DAOFactory implementation
 * used by the service. The the documentation of AuctionService for more information.
 *
 * After running some persistence-related tests, the main() method will print out the
 * number of SQL statements executed. Your goal is to have as few SQL statements as
 * possible. If you enable DEBUG logging for this class, it will print a list of all
 * SQL statements that have been executed, otherwise, INFO will only print the final
 * result.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class BenchmarkSql {

    private static Log log = LogFactory.getLog(BenchmarkSql.class);

    // Temp variables
    Category rootCategory;

    User u1;
    User u2;
    User u3;
    User u4;

    Item i1;
    Item i2;
    Item i3;
    Item i4;

    AuctionService auctionSvc;

    public static void main(String[] args) throws Exception {
        // Instantiate service
        if (args.length != 2)
            printUsage();
        String serviceClassName = args[0];
        String daoFactoryName = args[1];
        if (serviceClassName == null || daoFactoryName == null)
            printUsage();

        BenchmarkSql test = new BenchmarkSql();
        test.auctionSvc = (AuctionService)Class.forName(serviceClassName).newInstance();

        try {
            test.auctionSvc.setUp(Class.forName(daoFactoryName));

            test.runCategoryTest();
            test.runUserTest();
            test.runItemTest();
            test.runBidTest();

        } finally{
            test.auctionSvc.tearDown();
            printSqlLog();
        }
    }

    private static void printUsage() throws Exception{
        System.out.println("Usage:");
        System.out.println(" java -cp <classpath> org.hibernate.auction.bench.client.BenchmarkSQL <service.Class> <dao.Factory>");
        throw new InstantiationException("Please set your service class and DAO factory!");
    }

    private static void printSqlLog() {
        if (log.isDebugEnabled()) {
            for (Iterator it = SqlLogger.getInstance().getFullSqlLog().iterator(); it.hasNext();)
                System.out.println(it.next());
        }
        log.info("No. of SELECTs: = " + SqlLogger.getInstance().getSelects().size());
        log.info("OUTER JOINs   : = " + SqlLogger.getInstance().getOuterJoins());
        log.info("No. of INSERTs: = " + SqlLogger.getInstance().getInserts().size());
        log.info("No. of UPDATEs: = " + SqlLogger.getInstance().getUpdates().size());
        log.info("No. of DELETEs: = " + SqlLogger.getInstance().getDeletes().size());
        SqlLogger.getInstance().clear();
    }

    private void runCategoryTest() throws Exception {

        // Create some categories
        SqlLogger.getInstance().getFullSqlLog().add("### Creating categories");
        auctionSvc.beginTransaction();
        rootCategory = DataHelper.createCategoryTree();
        class CategorySaver {
            public void saveCategory(Category cat) {
                auctionSvc.createCategory(cat);
                for (Iterator it = cat.getChildCategories().iterator(); it.hasNext();) {
                    Category child = (Category) it.next();
                    saveCategory(child);
                }
            }
        }
        CategorySaver catSaver = new CategorySaver();
        catSaver.saveCategory(rootCategory);
        auctionSvc.endTransaction();

        // Load a category and iterate all child categories recursively
        SqlLogger.getInstance().getFullSqlLog().add("### Loading categories and iterating through all children");
        auctionSvc.beginTransaction();
        Category root = auctionSvc.findCategoryById(rootCategory.getId(), false);
        class CategoryPrinter {
            public void printCategories(StringBuffer out, Category cat, int level) {
                level++;
                for (int i = 0; i < level; i++) out.append("#");
                out.append(" " + cat);
                out.append("\n");
                for (Iterator it = cat.getChildCategories().iterator(); it.hasNext();) {
                    Category child = (Category) it.next();
                    printCategories(out, child, level);
                }
            }
        }
        CategoryPrinter printer = new CategoryPrinter();
        StringBuffer buf = new StringBuffer();
        printer.printCategories(buf, root, 0);
//        if (log.isDebugEnabled())
//            log.debug(buf);
        auctionSvc.endTransaction();

        // Rename some categories
        SqlLogger.getInstance().getFullSqlLog().add("### Renaming some categories");
        auctionSvc.beginTransaction();
        for (Iterator it = rootCategory.getChildCategories().iterator(); it.hasNext();) {
            Category category = (Category) it.next();
            category.setName(DataHelper.generateRandomString(DataHelper.CATEGORY_NAMELENGTH));
            auctionSvc.storeCategory(category);
        }
        auctionSvc.endTransaction();

        // Delete a category (cascades automatically to children)
        SqlLogger.getInstance().getFullSqlLog().add("### Deleting a category, cascades to children");
        auctionSvc.beginTransaction();
        auctionSvc.deleteCategory((Category)rootCategory.getChildCategories().iterator().next());
        auctionSvc.endTransaction();
    }

    private void runUserTest() {

        // Create some users
        SqlLogger.getInstance().getFullSqlLog().add("### Creating some users");
        u1 = DataHelper.createUser();
        u2 = DataHelper.createUser();
        u3 = DataHelper.createUser();
        u4 = DataHelper.createUser();
        auctionSvc.beginTransaction();
        auctionSvc.createUser(u1);
        auctionSvc.createUser(u2);
        auctionSvc.createUser(u3);
        auctionSvc.createUser(u4);
        auctionSvc.endTransaction();

        // Rename some users
        SqlLogger.getInstance().getFullSqlLog().add("### Renaming some users");
        auctionSvc.beginTransaction();
        u1.setFirstname(DataHelper.generateRandomString(DataHelper.USER_NAMELENGTH));
        u2.setLastname(DataHelper.generateRandomString(DataHelper.USER_NAMELENGTH));
        auctionSvc.storeUser(u1);
        auctionSvc.storeUser(u2);
        auctionSvc.endTransaction();

        // Make an administrator
        SqlLogger.getInstance().getFullSqlLog().add("### Setting a user to administrator");
        auctionSvc.beginTransaction();
        User user1 = auctionSvc.findUserById(u1.getId(), false);
        user1.setAdmin(true);
        auctionSvc.storeUser(user1);
        auctionSvc.endTransaction();

        // Access BillingDetails and Address
        SqlLogger.getInstance().getFullSqlLog().add("### Accessing billing details and address of a user");
        auctionSvc.beginTransaction();
        User user2 = auctionSvc.findUserById(u1.getId(), false);
        Address address1 = user2.getAddress();
        BillingDetails billingDetails1 = user2.getDefaultBillingDetails();
        auctionSvc.endTransaction();
        if (!address1.equals(u1.getAddress()))
            throw new RuntimeException("Address of User was incorrect.");
        if (!billingDetails1.getId().equals(u1.getDefaultBillingDetails().getId()))
            throw new RuntimeException("BillingDetails of User was incorrect.");

    }

    private void runItemTest() {

        // Create some items
        SqlLogger.getInstance().getFullSqlLog().add("### Creating some items");
        auctionSvc.beginTransaction();
        User user1 = auctionSvc.findUserById(u1.getId(), false);
        i1 = DataHelper.createItem(user1);
        auctionSvc.createItem(i1);
        i2 = DataHelper.createItem(user1);
        auctionSvc.createItem(i2);
        i3 = DataHelper.createItem(user1);
        auctionSvc.createItem(i3);

        User user2 = auctionSvc.findUserById(u2.getId(), false);
        i4 = DataHelper.createItem(user2);
        auctionSvc.storeItem(i4);

        auctionSvc.endTransaction();

        // Put some items in categories
        SqlLogger.getInstance().getFullSqlLog().add("### Putting items into categories");
        auctionSvc.beginTransaction();
        i1.addCategory(rootCategory);
        i2.addCategory(rootCategory);
        i3.addCategory(rootCategory);
        auctionSvc.storeItem(i1);
        auctionSvc.storeItem(i2);
        auctionSvc.storeItem(i3);
        auctionSvc.storeCategory(rootCategory);
        auctionSvc.endTransaction();

        // Change description of some items
        SqlLogger.getInstance().getFullSqlLog().add("### Changing description of some items");
        auctionSvc.beginTransaction();
        Item item1 = auctionSvc.findItemById(i1.getId(), false);
        item1.setDescription(DataHelper.generateRandomString(99));
        auctionSvc.storeItem(item1);
        auctionSvc.endTransaction();

        // Find items by example (null create date, but keep ItemState)
        SqlLogger.getInstance().getFullSqlLog().add("### Finding items by example");
        auctionSvc.beginTransaction();
        Item exampleItem = new Item(null, null, null,
                                    new MonetaryAmount(new BigDecimal("1.00"), Currency.getInstance(Locale.US)),
                                    null, null, null);
        exampleItem.setCreated(null);
        Collection exampleItems = auctionSvc.findItemsByExample(exampleItem);
        auctionSvc.endTransaction();
        if (exampleItems.size() != 4)
            throw new RuntimeException("Result of example query was incorrect.");

        // Approve some items for auction
        SqlLogger.getInstance().getFullSqlLog().add("### Approving some items for auction");
        auctionSvc.beginTransaction();
        Item item1b = auctionSvc.findItemById(i1.getId(), false);
        Item item2b = auctionSvc.findItemById(i2.getId(), false);
        User user1b = auctionSvc.findUserById(u1.getId(), false);
        item1b.setPendingForApproval();
        item1b.approve(user1b);
        item2b.setPendingForApproval();
        item2b.approve(user1b);
        auctionSvc.storeItem(item1b);
        auctionSvc.storeItem(item2b);
        auctionSvc.endTransaction();

    }

    public void runBidTest() {

        // Create some bids
        SqlLogger.getInstance().getFullSqlLog().add("### Placing bids");
        auctionSvc.beginTransaction();

        Item item1 = auctionSvc.findItemById(i1.getId(), false);
        Item item2 = auctionSvc.findItemById(i2.getId(), false);
        User user1 = auctionSvc.findUserById(u1.getId(), false);
        User user2 = auctionSvc.findUserById(u2.getId(), false);

        Bid item1MaxBid = auctionSvc.getCurrentMaxBid(item1);
        Bid item1MinBid = auctionSvc.getCurrentMinBid(item1);
        item1.placeBid(user1,
                       new MonetaryAmount(new BigDecimal("2.00"), Currency.getInstance(Locale.US)),
                       item1MaxBid, item1MinBid);

        item1MaxBid = auctionSvc.getCurrentMaxBid(item1);
        item1MinBid = auctionSvc.getCurrentMinBid(item1);
        item1.placeBid(user2,
                       new MonetaryAmount(new BigDecimal("3.00"), Currency.getInstance(Locale.US)),
                       item1MaxBid, item1MinBid);

        item1MaxBid = auctionSvc.getCurrentMaxBid(item1);
        item1MinBid = auctionSvc.getCurrentMinBid(item1);
        item1.placeBid(user1,
                       new MonetaryAmount(new BigDecimal("4.00"), Currency.getInstance(Locale.US)),
                       item1MaxBid, item1MinBid);

        item1MaxBid = auctionSvc.getCurrentMaxBid(item1);
        item1MinBid = auctionSvc.getCurrentMinBid(item1);
        item1.placeBid(user2,
                       new MonetaryAmount(new BigDecimal("5.00"), Currency.getInstance(Locale.US)),
                       item1MaxBid, item1MinBid);

        item1MaxBid = auctionSvc.getCurrentMaxBid(item1);
        item1MinBid = auctionSvc.getCurrentMinBid(item1);
        item1.placeBid(user1,
                       new MonetaryAmount(new BigDecimal("6.00"), Currency.getInstance(Locale.US)),
                       item1MaxBid, item1MinBid);

        Bid item2MaxBid = auctionSvc.getCurrentMaxBid(item2);
        Bid item2MinBid = auctionSvc.getCurrentMinBid(item2);
        item2.placeBid(user2,
                       new MonetaryAmount(new BigDecimal("2.00"), Currency.getInstance(Locale.US)),
                       item2MaxBid, item2MinBid);

        auctionSvc.storeItem(item1);
        auctionSvc.storeItem(item2);

        auctionSvc.endTransaction();

        // Find self-bidders
        SqlLogger.getInstance().getFullSqlLog().add("### Find users who bid for themselves");
        auctionSvc.beginTransaction();
        Collection usersWithSelfBid = auctionSvc.findUsersWithSelfBid();
        User fool = (User) usersWithSelfBid.iterator().next();
        auctionSvc.endTransaction();
        if (!fool.getId().equals(u1.getId()))
            throw new RuntimeException("Result of self-bid check was incorrect.");

        // Report average bids
        SqlLogger.getInstance().getFullSqlLog().add("### Reporting average bid amounts");
        auctionSvc.beginTransaction();
        Category root = auctionSvc.findCategoryById(rootCategory.getId(), false);
        Collection items = auctionSvc.findItemsInCategory(root);
        ItemAvgBidAmount[] itemAvgBids = auctionSvc.reportAverageBids(items);
        auctionSvc.endTransaction();
        if (itemAvgBids.length != 2)
            throw new RuntimeException("Result of report query was incorrect.");

    }


}
