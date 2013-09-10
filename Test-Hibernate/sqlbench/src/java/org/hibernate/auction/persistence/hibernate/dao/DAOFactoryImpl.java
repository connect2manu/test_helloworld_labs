package org.hibernate.auction.persistence.hibernate.dao;

import org.hibernate.auction.persistence.dao.*;

public class DAOFactoryImpl extends DAOFactory {

    public CategoryDAO getCategoryDAO() {
        return new CategoryDAOImpl();
    }

    public ItemDAO getItemDAO() {
        return new ItemDAOImpl();
    }

    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }
}
