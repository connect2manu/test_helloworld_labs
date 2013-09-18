package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.dao.*;
import org.hibernate.ce.auction.exceptions.*;
import org.hibernate.ce.auction.model.*;

import java.math.BigDecimal;
import java.util.*;

public class ItemTest extends TestCaseWithData {

    // ********************************************************** //

    public void testItemData() throws Exception {

        ItemDAO itemDAO = DAOFACTORY.getItemDAO();

        Item a1 = itemDAO.findById(auctionOne.getId(), false);
        assertEquals(a1.getInitialPrice(),
                new MonetaryAmount(new BigDecimal("1.99"), Currency.getInstance(Locale.US)));

    }

    // ********************************************************** //

    public void testPlaceBid() throws Exception {

        // TODO: Test doesn't consider currency properly!

        ItemDAO itemDAO = DAOFACTORY.getItemDAO();
        UserDAO userDAO = DAOFACTORY.getUserDAO();

        Bid currentMaxBid = itemDAO.getMaxBid(auctionTwo.getId());
        Bid currentMinBid = itemDAO.getMinBid(auctionTwo.getId());
        Item a2 = (Item) itemDAO.findById(auctionTwo.getId(), true);

        // Fail, auction is not active yet
        try {
            BigDecimal bidAmount = new BigDecimal("99.99");
            MonetaryAmount newAmount = new MonetaryAmount(bidAmount, Currency.getInstance("USD"));
            a2.placeBid(userDAO.findById(u3.getId(), false),
                    newAmount,
                    currentMaxBid,
                    currentMinBid);
        } catch (BusinessException success) {
        }

        // Fail, user isn't an admin
        try {
            a2.approve(u3);
        } catch (PermissionException success) {
        }

        // Success, set active
        a2.setPendingForApproval();
        a2.approve(u1);

        // Success, place a bid
        try {
            BigDecimal bidAmount = new BigDecimal("100.00");
            MonetaryAmount newAmount = new MonetaryAmount(bidAmount, Currency.getInstance("USD"));
            a2.placeBid(userDAO.findById(u3.getId(), false),
                    newAmount,
                    currentMaxBid,
                    currentMinBid);

        } catch (BusinessException failure) {
            throw failure;
        }

        // Fail, bid amount is too low
        try {
            BigDecimal bidAmount = new BigDecimal("99.99");
            MonetaryAmount newAmount = new MonetaryAmount(bidAmount, Currency.getInstance("USD"));
            a2.placeBid(userDAO.findById(u3.getId(), false),
                    newAmount,
                    currentMaxBid,
                    currentMinBid);
        } catch (BusinessException success) {
        }

        // TODO: Implement test for auction dates...

    }

    // ********************************************************** //

}
