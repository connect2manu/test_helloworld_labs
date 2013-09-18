package org.hibernate.auction.persistence.dao;

public abstract class DAOFactory {

    public static DAOFactory getInstance(Class factoryImpl) throws Exception {
        return (DAOFactory)factoryImpl.newInstance();
    }

    public abstract CategoryDAO getCategoryDAO();
    public abstract ItemDAO getItemDAO();
    public abstract UserDAO getUserDAO();
}
