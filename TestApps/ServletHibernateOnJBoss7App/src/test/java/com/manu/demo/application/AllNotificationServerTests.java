package com.manu.demo.application;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.manu.demo.application.httpservlet.ReadNotificationTest;
import com.manu.demo.application.httpservlet.WriteNotificationTest;
import com.manu.demo.application.util.AppUtilsTest;

/**
 * Run all unit tests for Notification Server.
 * 
 * @author manu.mehrotra
 */
@RunWith(Suite.class)
@SuiteClasses({})
public class AllNotificationServerTests {

	public static Test suite() {
		TestSuite suite = new TestSuite();

		suite.addTestSuite(AppUtilsTest.class);
		suite.addTestSuite(WriteNotificationTest.class);
		suite.addTestSuite(ReadNotificationTest.class);

		return suite;
	}

	public static void main(String args[]) {
		TestRunner.run(suite());
	}
}
