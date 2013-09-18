package org.hibernate.ce.auction.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.ce.auction.dao.CategoryDAO;
import org.hibernate.ce.auction.model.*;
import org.hibernate.criterion.Expression;
import org.jboss.seam.annotations.*;

import java.util.Collection;

import static org.jboss.seam.ScopeType.EVENT;

/**
 * Hibernate-specific implementation of the <tt>CategoryDAO</tt> non-CRUD data access object.
 *
 * @author christian.bauer@jboss.com
 */
@Name("categoryDAO")
@Scope(EVENT)
public class CategoryDAOHibernate
        extends     GenericHibernateDAO<Category, Long>
        implements  CategoryDAO {

    @In(value = "caveatemptorDatabase", create = true)
    public void setSession(Session s) {
        session = s;
    }

    public CategoryDAOHibernate() {
        super(Category.class);
    }

    public CategoryDAOHibernate(Session session) {
        super(Category.class, session);
    }

    public Collection<Category> findAll(boolean onlyRootCategories) {
        if (onlyRootCategories)
            return findByCriteria( Expression.isNull("parent") );
        else
            return findAll();
    }
}
