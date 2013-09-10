package org.hibernate.ce.auction.test.junit;

import junit.framework.*;
import junit.textui.TestRunner;
import org.hibernate.ce.auction.test.junit.hibernate.*;

/**
 * Run all unit tests for CaveatEmptor.
 * <p>
 * Does not include the tests that need bytecode instrumentation. See the
 * build.xml file for separate targets.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class AllHibernateTests {

    public static Test suite() {
        TestSuite suite = new TestSuite();

        suite.addTestSuite( UserTest.class );
        suite.addTestSuite( ItemTest.class );
        suite.addTestSuite( CategoryItemTest.class );
        suite.addTestSuite( AuditTest.class );
        suite.addTestSuite( NestedSetTest.class );
        suite.addTestSuite( ShipmentTest.class );

        return suite;
    }

    public static void main(String args[]) {
        TestRunner.run( suite() );
    }
}
