package org.hibernate.ce.auction.dao;

import org.hibernate.ce.auction.model.Category;

import java.util.Collection;

/**
 * Business DAO operations related to the <tt>Category</tt> entity.
 *
 * @see Category
 *
 * @author christian.bauer@jboss.com
 */
public interface CategoryDAO extends GenericDAO<Category, Long> {

    public Collection<Category> findAll(boolean onlyRootCategories);

}
