package org.hibernate.auction.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.hibernate.auction.dao.UserDAO;
import org.hibernate.auction.model.CreditCard;
import org.hibernate.auction.model.CreditCardType;
import org.hibernate.auction.model.User;
import org.hibernate.auction.persistence.HibernateUtil;

public class UserTest extends TestCaseWithData {

	// ********************************************************** //

	public void testBillingDetails() throws Exception {
		initData();

		UserDAO userDAO= new UserDAO();

        User user1 = userDAO.getUserById(u1.getId(), false);

		// load() trick to retrieve the subclass instance instead of a superclass proxy
		CreditCard cc =
		        (CreditCard)HibernateUtil.getSession().load(CreditCard.class, user1.getDefaultBillingDetails().getId());
		assertEquals( cc.getType(), CreditCardType.MASTERCARD);

		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
	}

	// ********************************************************** //

	public UserTest(String x) {
		super(x);
	}

	public static Test suite() {
		return new TestSuite(UserTest.class);
	}

	public static void main(String[] args) throws Exception {
		TestRunner.run( suite() );
	}

}
