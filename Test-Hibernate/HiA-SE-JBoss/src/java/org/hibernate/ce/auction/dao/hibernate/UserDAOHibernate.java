package org.hibernate.ce.auction.dao.hibernate;

import org.hibernate.*;
import org.hibernate.ce.auction.dao.UserDAO;
import org.hibernate.ce.auction.model.*;
import org.jboss.seam.annotations.*;
import static org.jboss.seam.ScopeType.EVENT;
import static org.hibernate.criterion.Expression.*;

@Name("userDAO")
@Scope(EVENT)
public class UserDAOHibernate
        extends GenericHibernateDAO<User, Long>
        implements UserDAO {

    @In(value="caveatemptorDatabase", create=true)
    public void setSession(Session s) {
        session = s;
    }

    public UserDAOHibernate() {
        super(User.class);
    }

    public UserDAOHibernate(Session session) {
        super(User.class, session);
    }

    public User validateLogin(User user) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        crit.add( eq("username", user.getUsername()) );
        crit.add( eq("password", user.getPassword()) );
        return (User)crit.uniqueResult();
    }

    // Could be in a separate interface..
    public void persistAddress(AddressEntity address) {
        getSession().save(address);
    }

}

