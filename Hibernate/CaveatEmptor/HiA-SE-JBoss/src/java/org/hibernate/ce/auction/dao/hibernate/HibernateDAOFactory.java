package org.hibernate.ce.auction.dao.hibernate;

import org.hibernate.ce.auction.dao.*;
import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.persistence.HibernateUtil;
import org.hibernate.Session;
import org.jboss.seam.annotations.*;
import static org.jboss.seam.ScopeType.*;

/**
 * Returns Hibernate-specific instances of DAOs.
 * <p/>
 * One of the responsiblities of the factory is to inject a Hibernate Session
 * into the DAOs. You can customize the getCurrentSession() method if you
 * are not using the default strategy, which simply delegates to
 * Hibernates built-in "current Session" mechanism. This factory uses
 * HibernateUtil to get access to the SessionFactory.
 * <p/>
 * If for a particular DAO there is no additional non-CRUD functionality, we use
 * a nested static class to implement the interface in a generic way. This allows clean
 * refactoring later on, should the interface implement business data access
 * methods at some later time. Then, we would externalize the implementation into
 * its own first-level class.
 *
 * @author christian.bauer@jboss.com
 */
public class HibernateDAOFactory extends DAOFactory {

    public ItemDAO getItemDAO() {
        return new ItemDAOHibernate(getCurrentSession());
    }

    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOHibernate(getCurrentSession());
    }

    public CommentDAO getCommentDAO() {
        return new CommentDAOHibernate(getCurrentSession());
    }

    public UserDAO getUserDAO() {
        return new UserDAOHibernate(getCurrentSession());
    }

    public CategorizedItemDAO getCategorizedItemDAO() {
        return new CategorizedItemDAOHibernate(getCurrentSession());
    }

    public BillingDetailsDAO getBillingDetailsDAO() {
        return new BillingDetailsDAOHibernate(getCurrentSession());
    }

    public ShipmentDAO getShipmentDAO() {
        return new ShipmentDAOHibernate(getCurrentSession());
    }

    // You could override this if you don't want HibernateUtil for lookup
    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    // Inline concrete DAO implementations with no business-related data access methods.
    // If we use public static nested classes, we can centralize all of them in one source file.

    @Name("categorizedItemDAO")
    @Scope(EVENT)
    public static class CategorizedItemDAOHibernate
            extends GenericHibernateDAO<CategorizedItem, CategorizedItem.Id>
            implements CategorizedItemDAO {

        @In(value = "caveatemptorDatabase", create = true)
        public void setSession(Session s) {
            session = s;
        }

        public CategorizedItemDAOHibernate() {
            super(CategorizedItem.class);
        }

        public CategorizedItemDAOHibernate(Session session) {
            super(CategorizedItem.class, session);
        }

    }

    @Name("commentDAO")
    @Scope(EVENT)
    public static class CommentDAOHibernate
            extends GenericHibernateDAO<Comment, Long>
            implements CommentDAO {

        @In(value = "caveatemptorDatabase", create = true)
        public void setSession(Session s) {
            session = s;
        }

        public CommentDAOHibernate() {
            super(Comment.class);
        }

        public CommentDAOHibernate(Session session) {
            super(Comment.class, session);
        }

    }

    @Name("shipmentDAO")
    @Scope(EVENT)
    public static class ShipmentDAOHibernate
            extends GenericHibernateDAO<Shipment, Long>
            implements ShipmentDAO {

        @In(value = "caveatemptorDatabase", create = true)
        public void setSession(Session s) {
            session = s;
        }

        public ShipmentDAOHibernate() {
            super(Shipment.class);
        }

        public ShipmentDAOHibernate(Session session) {
            super(Shipment.class, session);
        }

    }

}
