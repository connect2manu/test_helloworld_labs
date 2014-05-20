package com.p2p.novell;

import javax.naming.InitialContext;

import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.MessageListener;
import javax.jms.JMSException;
import javax.jms.ExceptionListener;
import javax.jms.QueueSession;
import javax.jms.QueueReceiver;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

public class AsyncReceiver implements MessageListener, ExceptionListener {
	public static void main(String[] args) throws Exception {
		// get the initial context
		InitialContext ctx = new InitialContext();

		// lookup the queue object
		Queue queue = (Queue) ctx.lookup("queue/queue0");

		// lookup the queue connection factory
		QueueConnectionFactory connFactory = (QueueConnectionFactory) ctx.lookup("queue/connectionFactory");

		// create a queue connection
		QueueConnection queueConn = connFactory.createQueueConnection();

		// create a queue session
		QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		// create a queue receiver
		QueueReceiver queueReceiver = queueSession.createReceiver(queue);

		// set an asynchronous message listener
		AsyncReceiver asyncReceiver = new AsyncReceiver();
		queueReceiver.setMessageListener(asyncReceiver);

		// set an asynchronous exception listener on the connection
		queueConn.setExceptionListener(asyncReceiver);

		// start the connection
		queueConn.start();

		// wait for messages
		System.out.print("waiting for messages");
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			System.out.print(".");
		}
		System.out.println();

		// close the queue connection
		queueConn.close();
	}

	/**
	 * This method is called asynchronously by JMS when a message arrives at the
	 * queue. Client applications must not throw any exceptions in the onMessage
	 * method.
	 * 
	 * @param message
	 *            A JMS message.
	 */
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("received: " + msg.getText());
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method is called asynchronously by JMS when some error occurs. When
	 * using an asynchronous message listener it is recommended to use an
	 * exception listener also since JMS have no way to report errors otherwise.
	 * 
	 * @param exception
	 *            A JMS exception.
	 */
	public void onException(JMSException exception) {
		System.err.println("an error occurred: " + exception);
	}
}