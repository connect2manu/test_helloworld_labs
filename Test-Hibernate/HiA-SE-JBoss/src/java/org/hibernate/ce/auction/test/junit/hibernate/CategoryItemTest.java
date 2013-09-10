package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.persistence.HibernateUtil;
import org.hibernate.ce.auction.dao.CategorizedItemDAO;

import java.util.*;

import org.hibernate.*;

public class CategoryItemTest extends TestCaseWithData {

	// ********************************************************** //

	public void testCompositeQuery() throws Exception {

		// Query for Category and all categorized Items (three tables joined)
		Query q = sessionFactory.getCurrentSession()
                .createQuery("select c from Category as c" +
                             " left join fetch c.categorizedItems as ci" +
                             " join fetch ci.item as i");
		Collection result = new HashSet(q.list());
		assertTrue(result.size() == 2);

        sessionFactory.getCurrentSession().clear();

		// Check initialization (should be eager fetched)
		for (Iterator it = result.iterator(); it.hasNext();) {
			Category cat = (Category) it.next();
			for (Iterator it2 = cat.getCategorizedItems().iterator(); it2.hasNext();) {
				assertTrue(it2.next() != null);
			}
		}
	}

    public void testFindByCompositeId() throws Exception {
        CategorizedItemDAO dao = DAOFACTORY.getCategorizedItemDAO();

        CategorizedItem.Id id = new CategorizedItem.Id( carsLuxury.getId(), auctionOne.getId());
        CategorizedItem catItem = dao.findById(id, false);

        assertEquals(u1.getUsername(), catItem.getUsername());
    }

	public void testDeletionFromItem() throws Exception {

		// Delete all links for auctionFour by clearing collection
		Item i = (Item)sessionFactory.getCurrentSession().get(Item.class, auctionFour.getId());
        assertTrue(i.getCategorizedItems().size() == 2);
		i.getCategorizedItems().clear();

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();

		// Check deletion
		CategorizedItem catItem = (CategorizedItem)sessionFactory.getCurrentSession().get(CategorizedItem.class,
					new CategorizedItem.Id(carsLuxury.getId(), auctionFour.getId()));
		assertTrue(catItem == null);
        i = (Item)sessionFactory.getCurrentSession().get(Item.class, auctionFour.getId());
        assertTrue(i.getCategorizedItems().size() == 0);

	}

	public void testCategorizedItemOrphansDeletion() throws Exception {

		Category c = (Category)sessionFactory.getCurrentSession().get(Category.class, carsSUV.getId());
        assertTrue(c.getCategorizedItems().size() == 2);
        // Remove associations from item side and then from category collection
        for (Iterator it = c.getCategorizedItems().iterator(); it.hasNext();) {
            CategorizedItem categorizedItem = (CategorizedItem) it.next();
            categorizedItem.getItem().getCategorizedItems().remove(categorizedItem);
            it.remove();
        }

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();

		// Check orphan deletion
		CategorizedItem catItem3 = (CategorizedItem)sessionFactory.getCurrentSession().get(CategorizedItem.class,
					new CategorizedItem.Id(carsSUV.getId(), auctionThree.getId()));
		assertTrue(catItem3 == null);
        CategorizedItem catItem4 = (CategorizedItem)sessionFactory.getCurrentSession().get(CategorizedItem.class,
                    new CategorizedItem.Id(carsSUV.getId(), auctionFour.getId()));
        assertTrue(catItem4 == null);
        c = (Category)sessionFactory.getCurrentSession().get(Category.class, carsSUV.getId());
        assertTrue(c.getCategorizedItems().size() == 0);

	}

	// ********************************************************** //

}
