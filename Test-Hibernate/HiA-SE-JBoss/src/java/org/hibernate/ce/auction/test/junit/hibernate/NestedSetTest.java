package org.hibernate.ce.auction.test.junit.hibernate;

import org.hibernate.ce.auction.model.*;
import org.hibernate.ce.auction.persistence.*;
import org.hibernate.*;
import org.hibernate.context.ThreadLocalSessionContext;
import org.hibernate.cfg.Environment;

public class NestedSetTest extends HibernateTest {

    // This test does its own transaction handling
    protected void runTest() throws Throwable {
        wrapInTransaction = false;
        super.runTest();
    }

	// ********************************************************** //

	public void testTreeCreation() throws Exception {

        // This test needs extended Session for long conversations
        HibernateUtil.getConfiguration().setProperty(
                Environment.CURRENT_SESSION_CONTEXT_CLASS,
                ExtendedThreadLocalSessionContext.class.getName()
        );

        // Register interceptor and rebuild
        NestedSetInterceptor interceptor = new NestedSetInterceptor();
        sessionFactory = HibernateUtil.registerInterceptorAndRebuild(interceptor);

        interceptor.setOwningSession(sessionFactory.getCurrentSession());
        sessionFactory.getCurrentSession().beginTransaction();
        
        Category a = new Category("A");
        Category b = new Category("B");
        Category c = new Category("C");

        a.addChild(b);
        b.addChild(c);

        sessionFactory.getCurrentSession().saveOrUpdate(c);
        sessionFactory.getCurrentSession().saveOrUpdate(b);
        sessionFactory.getCurrentSession().saveOrUpdate(a);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        Session s = ExtendedThreadLocalSessionContext.unbind(sessionFactory);

        assertEquals( 1,  a.getNodeInfo().getLeft()); assertEquals( 6, a.getNodeInfo().getRight() );
        assertEquals( 2,  b.getNodeInfo().getLeft()); assertEquals( 5,  b.getNodeInfo().getRight() );
        assertEquals( 3,  c.getNodeInfo().getLeft()); assertEquals( 4,  c.getNodeInfo().getRight() );

        assertTrue( a.getId().equals(a.getNodeInfo().getThread()) );
        assertTrue( a.getId().equals(b.getNodeInfo().getThread()) );
        assertTrue( a.getId().equals(c.getNodeInfo().getThread()) );

        // ########################################################

        s.beginTransaction();
        ExtendedThreadLocalSessionContext.bind(s);

        a.setName("A2");
        Category d = new Category("D");
        b.addChild(d);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        s = ExtendedThreadLocalSessionContext.unbind(sessionFactory);

        assertEquals( 1,  a.getNodeInfo().getLeft()); assertEquals( 8, a.getNodeInfo().getRight() );
        assertEquals( 2,  b.getNodeInfo().getLeft()); assertEquals( 7,  b.getNodeInfo().getRight() );
        assertEquals( 3,  c.getNodeInfo().getLeft()); assertEquals( 4,  c.getNodeInfo().getRight() );
        assertEquals( 5,  d.getNodeInfo().getLeft()); assertEquals( 6,  d.getNodeInfo().getRight() );
        assertTrue( a.getId().equals(d.getNodeInfo().getThread()) );

        // ########################################################

        s.beginTransaction();
        ExtendedThreadLocalSessionContext.bind(s);

        b.removeChild(d);
        c.addChild(d);
        Category e = new Category("E");
        b.addChild(e);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        s = ExtendedThreadLocalSessionContext.unbind(sessionFactory);

        assertEquals( 1,  a.getNodeInfo().getLeft()); assertEquals( 10, a.getNodeInfo().getRight() );
        assertEquals( 2,  b.getNodeInfo().getLeft()); assertEquals( 9,  b.getNodeInfo().getRight() );
        assertEquals( 3,  c.getNodeInfo().getLeft()); assertEquals( 6,  c.getNodeInfo().getRight() );
        assertEquals( 4,  d.getNodeInfo().getLeft()); assertEquals( 5,  d.getNodeInfo().getRight() );
        assertEquals( 7,  e.getNodeInfo().getLeft()); assertEquals( 8,  e.getNodeInfo().getRight() );
        assertTrue( a.getId().equals(e.getNodeInfo().getThread()) );

        // ########################################################

        s.beginTransaction();
        ExtendedThreadLocalSessionContext.bind(s);

        b.removeChild(c);
        sessionFactory.getCurrentSession().delete(c);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        s = ExtendedThreadLocalSessionContext.unbind(sessionFactory);

        assertEquals( 1,  a.getNodeInfo().getLeft()); assertEquals( 6, a.getNodeInfo().getRight() );
        assertEquals( 2,  b.getNodeInfo().getLeft()); assertEquals( 5,  b.getNodeInfo().getRight() );
        assertEquals( 3,  e.getNodeInfo().getLeft()); assertEquals( 4,  e.getNodeInfo().getRight() );

        // ########################################################

        s.beginTransaction();
        ExtendedThreadLocalSessionContext.bind(s);

        b.removeChild(e);
        sessionFactory.getCurrentSession().delete(e);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        sessionFactory.getCurrentSession().close(); // End of conversation

        assertEquals( 1,  a.getNodeInfo().getLeft()); assertEquals( 4, a.getNodeInfo().getRight() );
        assertEquals( 2,  b.getNodeInfo().getLeft()); assertEquals( 3,  b.getNodeInfo().getRight() );

        // ########################################################

        s = sessionFactory.getCurrentSession();
        interceptor.setOwningSession(s);
        sessionFactory.getCurrentSession().beginTransaction();

        Category b2 = new Category("B2");
        a.addChild(b2);
        sessionFactory.getCurrentSession().saveOrUpdate(a);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().getTransaction().commit();
        sessionFactory.getCurrentSession().close(); // End of conversation

        assertEquals( 1,  a.getNodeInfo().getLeft()); assertEquals( 6, a.getNodeInfo().getRight() );
        assertEquals( 2,  b.getNodeInfo().getLeft()); assertEquals( 3,  b.getNodeInfo().getRight() );
        assertEquals( 4,  b2.getNodeInfo().getLeft()); assertEquals( 5,  b2.getNodeInfo().getRight() );

        // Cleanup configuration
        HibernateUtil.getConfiguration().setProperty(
                Environment.CURRENT_SESSION_CONTEXT_CLASS,
                ThreadLocalSessionContext.class.getName()
        );
        // Unregister interceptor and rebuild
        HibernateUtil.resetInterceptor();
        HibernateUtil.rebuildSessionFactory();

	}

	// ********************************************************** //

}
