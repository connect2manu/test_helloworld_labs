package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.persistence.HibernateUtil;
import org.hibernate.ce.auction.model.User;
import org.hibernate.Query;

public class OracleTest extends TestCaseWithData {

    public void testStoredProcedureQuery() throws Exception {
        Query q = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("loadUsersByRank");
        q.setParameter("rank", 12);
        assertTrue(q.list().size() == 2);
    }

    public void testStoredProcedureQueryAutomapping() throws Exception {
        Query q = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("loadUsersByRankAuto");
        q.setParameter("rank", 12);
        assertTrue(q.list().size() == 2);
    }

    public void testStoredProcedureEntityUpdate() throws Exception {
        User testUser = (User)DAOFACTORY.getUserDAO().findById(u1.getId(), false);
        testUser.setEmail("foo@bar.baz");
        testUser.setAdmin(false);
        testUser.getBillingAddress().setStreet("Foostreet");
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();

        testUser = (User)DAOFACTORY.getUserDAO().findById(u1.getId(), false);
        assertEquals(testUser.getEmail(), "foo@bar.baz");
        assertFalse(testUser.isAdmin());
        assertEquals(testUser.getBillingAddress().getStreet(), "Foostreet");
    }

    public void testStoredFunctionScalarQueryFailureExpected() throws Exception {
        /* TODO: http://opensource2.atlassian.com/projects/hibernate/browse/HHH-1020 */
        Query q = HibernateUtil.getSessionFactory().getCurrentSession().getNamedQuery("getUserRank");
        q.setParameter("userId", u1.getId());
        assertEquals( q.uniqueResult(), u1.getRanking() );
    }

    public void testStoredFunctionRestriction() throws Exception {
        Query q = HibernateUtil.getSessionFactory().getCurrentSession()
            .createQuery("from User u where u.ranking > get_user_rank(:userId)");
        q.setParameter("userId", u1.getId());
        assertTrue(q.list().size() == 2);
    }

}
