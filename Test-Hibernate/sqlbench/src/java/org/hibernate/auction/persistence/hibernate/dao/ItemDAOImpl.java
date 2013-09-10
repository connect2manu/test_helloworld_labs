package org.hibernate.auction.persistence.hibernate.dao;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Example;
import org.hibernate.auction.exceptions.InfrastructureException;
import org.hibernate.auction.model.*;
import org.hibernate.auction.persistence.hibernate.util.HibernateUtil;
import org.hibernate.auction.persistence.dao.ItemDAO;
import org.hibernate.auction.report.ItemAvgBidAmount;

import java.util.*;
import java.math.BigDecimal;

/**
 * A typical DAO for auction items using Hibernate.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class ItemDAOImpl implements ItemDAO {

	public ItemDAOImpl() {
		HibernateUtil.beginTransaction();
	}

	// ********************************************************** //

	public Item getItemById(Long itemId, boolean lock)
			throws InfrastructureException {

		Session session = HibernateUtil.getSession();
		Item item = null;
		try {
			if (lock) {
				item = (Item) session.load(Item.class, itemId, LockMode.UPGRADE);
			} else {
				item = (Item) session.load(Item.class, itemId);
			}
		}  catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return item;
	}

	// ********************************************************** //

	public Bid getMaxBid(Long itemId)
			throws InfrastructureException {

		Bid maxBidAmount = null;
		try {
			// Note the creative where-clause subselect expression...
			// TODO: This completely ignores currency
			String query = "select b from Bid b where b.amount.value = " +
			                "(select max(b.amount.value) from Bid b where b.item.id = :itemid)";
			Query q = HibernateUtil.getSession().createQuery(query);
			q.setLong("itemid", itemId.longValue());
			maxBidAmount = (Bid) q.uniqueResult();
		}
		catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return maxBidAmount;
	}

	// ********************************************************** //

	public Bid getMinBid(Long itemId)
			throws InfrastructureException {

		Bid maxBidAmount = null;
		try {
			// Note the creative where-clause subselect expression..
			// TODO: This completely ignores currency
			String query = "select b from Bid b where b.amount.value = " +
			                "(select min(b.amount.value) from Bid b where b.item.id = :itemid)";
			Query q = HibernateUtil.getSession().createQuery(query);
			q.setLong("itemid", itemId.longValue());
			maxBidAmount = (Bid) q.uniqueResult();
		}
		catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return maxBidAmount;
	}

	// ********************************************************** //

	public Collection findAll()
			throws InfrastructureException {

		Collection items;
		try {
			items = HibernateUtil.getSession().createCriteria(Item.class).list();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return items;
	}

	// ********************************************************** //

	public Collection findByExample(Item exampleItem)
			throws InfrastructureException {

		Collection items;
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Item.class);
			items = crit.add(Example.create(exampleItem)).list();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return items;
	}

    // ********************************************************** //

    public ItemAvgBidAmount[] findAverageBids(Collection items)
            throws InfrastructureException {

        // TODO: This will be much better with the "select new" operator in Hibernate3
        Collection result;
        try {
            String query = "select i.id, avg(b.amount.value) from Item i join i.bids as b" +
                           " where i in (:items) group by i.id";
            Query q = HibernateUtil.getSession().createQuery(query);
            q.setParameterList("items", items);
            result = q.list();
        } catch (HibernateException ex) {
            throw new InfrastructureException(ex);
        }
        Collection itemAvgBidAmounts = new HashSet();
        for (Iterator it = result.iterator(); it.hasNext();) {
            Object[] objects = (Object[]) it.next();
            itemAvgBidAmounts.add(new ItemAvgBidAmount((Long)objects[0],
                                                       MonetaryAmount.fromString(objects[1].toString(), "USD")));
        }
        ItemAvgBidAmount[] itemAvgBidArray = new ItemAvgBidAmount[itemAvgBidAmounts.size()];
        return (ItemAvgBidAmount[])itemAvgBidAmounts.toArray(itemAvgBidArray);
    }

	// ********************************************************** //

	public void makePersistent(Item item)
			throws InfrastructureException {

		try {
			HibernateUtil.getSession().saveOrUpdate(item);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	// ********************************************************** //

	public void makeTransient(Item item)
			throws InfrastructureException {

		try {
			HibernateUtil.getSession().delete(item);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

}
