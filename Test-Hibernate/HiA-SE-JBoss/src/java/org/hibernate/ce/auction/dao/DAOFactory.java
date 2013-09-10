package org.hibernate.ce.auction.dao;

/**
 * Defines all DAOs and the concrete factories to get the conrecte DAOs.
 * <p>
 * Either use the <tt>DEFAULT</tt> to get the same concrete DAOFactory
 * throughout your application, or a concrete factory by name, e.g.
 * <tt>DAOFactory.HIBERNATE</tt> is a concrete <tt>HibernateDAOFactory</tt>.
 * <p>
 * Implementation: If you write a new DAO, this class has to know about it.
 * If you add a new persistence mechanism, add an additional concrete factory
 * for it to the enumeration of factories.
 * <p>
 * It probably wouldn't be a bad idea to move the <tt>DEFAULT</tt> setting
 * into external configuration.
 *
 * @author christian.bauer@jboss.com
 */
public abstract class DAOFactory {

    public static final DAOFactory EJB3_PERSISTENCE =
            new org.hibernate.ce.auction.dao.ejb3.Ejb3DAOFactory();

    public static final DAOFactory HIBERNATE =
            new org.hibernate.ce.auction.dao.hibernate.HibernateDAOFactory();

    public static final DAOFactory DEFAULT = HIBERNATE;

    // Add your DAO interfaces here
    public abstract ItemDAO getItemDAO();
    public abstract CategoryDAO getCategoryDAO();
    public abstract CommentDAO getCommentDAO();
    public abstract UserDAO getUserDAO();
    public abstract CategorizedItemDAO getCategorizedItemDAO();
    public abstract BillingDetailsDAO getBillingDetailsDAO();
    public abstract ShipmentDAO getShipmentDAO();

}
