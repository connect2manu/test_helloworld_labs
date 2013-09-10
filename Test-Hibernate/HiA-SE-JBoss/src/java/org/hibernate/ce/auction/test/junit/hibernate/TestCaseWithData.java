package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.dao.*;
import org.hibernate.ce.auction.model.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * No actual test, but only test data initialization.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public abstract class TestCaseWithData extends HibernateTest {

	// Keep references to domain objects
	Category cars;
	Category carsLuxury;
	Category carsSUV;

	User u1;
	User u2;
	User u3;

	Item auctionOne;
	Item auctionTwo;
	Item auctionThree;
	Item auctionFour;

	// ********************************************************** //

	/**
	 * Create test data for our domain model.
	 */
	protected void initData() {

		// Prepare DAOS
		CategoryDAO catDAO = DAOFACTORY.getCategoryDAO();
		UserDAO userDAO = DAOFACTORY.getUserDAO();
		ItemDAO itemDAO = DAOFACTORY.getItemDAO();
		CommentDAO commentDAO = DAOFACTORY.getCommentDAO();

		// Categories
		cars = new Category("Cars");
		carsLuxury = new Category("Luxury Cars");
		cars.addChild(carsLuxury);
		carsSUV = new Category("SUVs");
		cars.addChild(carsSUV);
		catDAO.makePersistent(cars);

		// Users
		u1 = new User("Christian", "Bauer", "turin", "abc123", "christian@hibernate.org");
		u1.setHomeAddress(new Address("Foo", "12345", "Bar"));
		u1.setBillingAddress(new Address("Bar", "3456", "Foo"));
		u1.setAdmin(true);
		u1.setRanking(11);
		u2= new User("Gavin", "King", "gavin", "abc123", "gavin@hibernate.org");
		u2.setHomeAddress(new Address("Foo", "12345", "Bar"));
        u2.setRanking(22);
		u3= new User("Max", "Andersen", "max", "abc123", "max@hibernate.org");
		u3.setHomeAddress(new Address("Foo", "12345", "Bar"));
        u3.setRanking(33);
		userDAO.makePersistent(u1);
		userDAO.makePersistent(u2);
		userDAO.makePersistent(u3);

        // Shipping addresses
        AddressEntity shippingAddress = new AddressEntity("foo", "bar", "baz");
        u1.setShippingAddress(shippingAddress);
        shippingAddress.setUser(u1);
        // TODO: ANN-144, Needed for annotations, no support for foreign generator.
        shippingAddress.setId(u1.getId());
        userDAO.persistAddress(shippingAddress);

		// BillingDetails
		BillingDetails ccOne = new CreditCard("Christian  Bauer", u1, "1234567890",
		                                        CreditCardType.MASTERCARD, "10", "2005");
		BillingDetails accOne = new BankAccount("Christian Bauer", u1, "234234234234",
		                                        "FooBar Rich Bank", "foobar123foobaz");
		u1.addBillingDetails(ccOne);
		u1.addBillingDetails(accOne);

		// Items
		Calendar inThreeDays = GregorianCalendar.getInstance();
		inThreeDays.roll(Calendar.DAY_OF_YEAR, 3);
		Calendar inFiveDays = GregorianCalendar.getInstance();
		inFiveDays.roll(Calendar.DAY_OF_YEAR, 5);
		Calendar nextWeek = GregorianCalendar.getInstance();
		nextWeek.roll(Calendar.WEEK_OF_YEAR, true);

		auctionOne = new Item("Item One", "An item in the carsLuxury category.",
		        u2,
		        new MonetaryAmount(new BigDecimal("1.99"), Currency.getInstance(Locale.US)),
		        new MonetaryAmount(new BigDecimal("50.33"), Currency.getInstance(Locale.US)),
		        new Date(), inThreeDays.getTime());
		auctionOne.getImages().add("imagefiledupe1.jpg");
		auctionOne.getImages().add("imagefiledupe1.jpg");
		auctionOne.getImages().add("imagefile2.jpg");
		auctionOne.setPendingForApproval();
		auctionOne.approve(u1);
		itemDAO.makePersistent(auctionOne);

        carsLuxury.getItems().add(auctionOne);
        // 1. alternative:
        new CategorizedItem(u1.getUsername(), carsLuxury, auctionOne);
        // 2. alternative
        carsLuxury.getCategorizedItemComponents().add(
                new CategorizedItemComponent(u1.getUsername(), carsLuxury, auctionOne)
        );
        // 3. alternative
        carsLuxury.getItemsAndUser().put(auctionOne, u1);

		auctionTwo = new Item("Item Two", "Another item in the carsLuxury category.",
				u2,
		        new MonetaryAmount(new BigDecimal("2.22"), Currency.getInstance(Locale.US)),
		        new MonetaryAmount(new BigDecimal("100.88"), Currency.getInstance(Locale.US)),
		        new Date(), inFiveDays.getTime());
		itemDAO.makePersistent(auctionTwo);
		new CategorizedItem(u1.getUsername(), carsLuxury, auctionTwo);

		auctionThree = new Item("Item Three", "Don't drive SUVs.",
				u2,
		        new MonetaryAmount(new BigDecimal("3.11"), Currency.getInstance(Locale.US)),
		        new MonetaryAmount(new BigDecimal("300.55"), Currency.getInstance(Locale.US)),
		        new Date(), inThreeDays.getTime());
		itemDAO.makePersistent(auctionThree);
		new CategorizedItem(u1.getUsername(), carsSUV, auctionThree);

		auctionFour = new Item("Item Four", "Really, not even luxury SUVs.",
				u1,
		        new MonetaryAmount(new BigDecimal("4.55"), Currency.getInstance(Locale.US)),
		        new MonetaryAmount(new BigDecimal("40.99"), Currency.getInstance(Locale.US)),
		        new Date(), nextWeek.getTime());
		itemDAO.makePersistent(auctionFour);
		new CategorizedItem(u1.getUsername(), carsLuxury, auctionFour);
		new CategorizedItem(u1.getUsername(), carsSUV, auctionFour);

		// Bids
		Bid bidOne1 = new Bid(new MonetaryAmount(new BigDecimal("12.12"), Currency.getInstance(Locale.US)),
		        auctionOne, u3);
		Bid bidOne2 = new Bid(new MonetaryAmount(new BigDecimal("13.13"), Currency.getInstance(Locale.US)),
		        auctionOne, u1);
		Bid bidOne3 = new Bid(new MonetaryAmount(new BigDecimal("14.14"), Currency.getInstance(Locale.US)),
		        auctionOne, u3);

		auctionOne.addBid(bidOne1);
		auctionOne.addBid(bidOne2);
		auctionOne.addBid(bidOne3);

		// Successful Bid
		auctionOne.setSuccessfulBid(bidOne3);

		// Comments
		Comment commentOne = new Comment(Rating.EXCELLENT, "This is Excellent.", u3, auctionOne);
		Comment commentTwo = new Comment(Rating.BAD, "This is very Low.", u1, auctionThree);
		commentDAO.makePersistent(commentOne);
		commentDAO.makePersistent(commentTwo);

	}

    public void inTransaction(){
        initData();
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

}
