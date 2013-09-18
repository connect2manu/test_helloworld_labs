package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.web.actions.*;

import java.util.*;
import java.math.BigDecimal;

/**
 * Test some aspectized services.
 */
public class ActionTest extends TestCaseWithData {

    // This test does its own transaction handling
    protected void runTest() throws Throwable {
        wrapInTransaction = false;
        super.runTest();
    }

    public void testPlaceBidAction() {

        sessionFactory.getCurrentSession().beginTransaction();
        inTransaction(); // Import test data
        sessionFactory.getCurrentSession().getTransaction().commit();

        // Prepare some input
        Map event = new HashMap();
        event.put(PlaceBidAction.INPUT_ITEM_ID, auctionOne.getId());
        event.put(PlaceBidAction.INPUT_USER_ID, u2.getId());
        event.put(PlaceBidAction.INPUT_BID_AMOUNT, new BigDecimal("1234.99"));

        // Execute the service method, interceptor aspect applies transaction
        Action placeBidAction = new PlaceBidAction();
        placeBidAction.execute(event);

        // Check if all good
        assertNotNull(event.get(PlaceBidAction.OUTPUT_NEW_BID));

    }

}
