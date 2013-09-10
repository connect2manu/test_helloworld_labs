package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.dao.UserDAO;
import org.hibernate.ce.auction.model.*;

public class UserTest extends TestCaseWithData {

    // ********************************************************** //

    public void testBillingDetails() throws Exception {

        UserDAO userDAO = DAOFACTORY.getUserDAO();

        User user1 = userDAO.findById(u1.getId(), false);

        // load() trick to retrieve the subclass instance instead of a superclass proxy
        CreditCard cc = (CreditCard) sessionFactory.getCurrentSession()
                .load(CreditCard.class, user1.getDefaultBillingDetails().getId());
        assertEquals( cc.getType(), CreditCardType.MASTERCARD);
    }

    // ********************************************************** //



}
