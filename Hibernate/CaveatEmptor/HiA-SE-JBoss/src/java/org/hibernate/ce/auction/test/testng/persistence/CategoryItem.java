package org.hibernate.ce.auction.test.testng.persistence;

import org.testng.annotations.Test;
import org.hibernate.ce.auction.dao.CategoryDAO;
import org.hibernate.ce.auction.test.testng.runtime.EJB3Container;
import org.hibernate.ce.auction.model.Category;

public class CategoryItem {

    @Test(groups = {"integration.database"})
    public void saveCategory() {

        CategoryDAO catDAO = (CategoryDAO)EJB3Container.lookup(CategoryDAO.class.getName());
        Category newCat = new Category("Foo");
        catDAO.makePersistent(newCat);

    }
}
