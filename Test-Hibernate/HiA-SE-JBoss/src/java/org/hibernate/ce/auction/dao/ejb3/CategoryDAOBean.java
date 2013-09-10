package org.hibernate.ce.auction.dao.ejb3;

import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.dao.*;

import javax.ejb.Stateless;
import javax.annotation.EJB;
import java.util.Collection;

/**
 * EJB3-specific implementation of the <tt>CategoryDAO</tt> non-CRUD data access object.
 *
 * @author christian.bauer@jboss.com
 */
@Stateless
public class CategoryDAOBean
        extends     GenericEjb3DAO<Category, Long>
        implements  CategoryDAO {

    @EJB
    ItemDAO itemDAO;
    
    public CategoryDAOBean() {
        super(Category.class);
    }

    @SuppressWarnings("unchecked")
    public Collection<Category> findAll(boolean onlyRootCategories) {
        if (onlyRootCategories)
            // Or bind ourself to a Hibernate extension and use findByCriteria(Criterion...) from superclass?
            return em.createQuery("from "+ getPersistentClass() + " c" +
                                  " where c.parent is null").getResultList();
        else
            return findAll();
    }
}
