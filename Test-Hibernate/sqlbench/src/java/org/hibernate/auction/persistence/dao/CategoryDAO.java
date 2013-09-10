package org.hibernate.auction.persistence.dao;

import org.hibernate.auction.model.Category;
import org.hibernate.auction.exceptions.InfrastructureException;

import java.util.Collection;

public interface CategoryDAO {
    Category getCategoryById(Long categoryId, boolean lock)
			throws InfrastructureException;

    Collection findAll(boolean onlyRootCategories)
			throws InfrastructureException;

    Collection findByExample(Category exampleCategory)
			throws InfrastructureException;

    Collection findItemsInCategory(Category category)
            throws InfrastructureException;

    void makePersistent(Category category)
			throws InfrastructureException;

    void makeTransient(Category category)
			throws InfrastructureException;
}
