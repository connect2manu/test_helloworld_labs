package org.hibernate.auction.persistence.hibernate.dao;

import net.sf.hibernate.*;
import net.sf.hibernate.expression.Example;
import net.sf.hibernate.expression.Expression;
import org.hibernate.auction.exceptions.InfrastructureException;
import org.hibernate.auction.model.Category;
import org.hibernate.auction.persistence.dao.CategoryDAO;
import org.hibernate.auction.persistence.hibernate.util.HibernateUtil;

import java.util.*;

/**
 * A typical DAO for categories using Hibernate.
 * 
 * @author Christian Bauer <christian@hibernate.org>
 */ 
public class CategoryDAOImpl implements CategoryDAO {

	public CategoryDAOImpl() {
		HibernateUtil.beginTransaction();
	}

	// ********************************************************** //

	public Category getCategoryById(Long categoryId, boolean lock)
			throws InfrastructureException {

		Session session = HibernateUtil.getSession();
		Category cat = null;
		try {
			if (lock) {
				cat = (Category) session.load(Category.class, categoryId, LockMode.UPGRADE);
			} else {
				cat = (Category) session.load(Category.class, categoryId);
			}
		}  catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return cat;
	}

	// ********************************************************** //

	public Collection findAll(boolean onlyRootCategories)
			throws InfrastructureException {

		Collection categories;
		try {
			if (onlyRootCategories) {
				Criteria crit = HibernateUtil.getSession().createCriteria(Category.class);
				categories = crit.add(Expression.isNull("parentCategory")).list();
			} else {
				categories = HibernateUtil.getSession().createCriteria(Category.class).list();
			}
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return categories;
	}

	// ********************************************************** //

	public Collection findByExample(Category exampleCategory)
			throws InfrastructureException {

		Collection categories;
		try {
			Criteria crit = HibernateUtil.getSession().createCriteria(Category.class);
			categories = crit.add(Example.create(exampleCategory)).list();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
		return categories;
	}

    // ********************************************************** //

    public Collection findItemsInCategory(Category category)
            throws InfrastructureException {

        Category cat;
        try {
            Criteria crit = HibernateUtil.getSession().createCriteria(Category.class);
            crit.add(Expression.eq("id", category.getId()));
            crit.setFetchMode("items", FetchMode.EAGER);
            cat = (Category)crit.uniqueResult();
        } catch (HibernateException ex) {
            throw new InfrastructureException(ex);
        }
        return cat.getItems();
    }


	// ********************************************************** //

	public void makePersistent(Category category)
			throws InfrastructureException {

		try {
			HibernateUtil.getSession().saveOrUpdate(category);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

	// ********************************************************** //

	public void makeTransient(Category category)
			throws InfrastructureException {
        try {
            HibernateUtil.getSession().delete(category);
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}

}
