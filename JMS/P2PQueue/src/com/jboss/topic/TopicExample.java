package com.jboss.topic;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.*;
import javax.naming.Context;

public class TopicExample implements MessageListener {

	public void example() throws Exception {
		String destinationName = "topic/topicA";

		Context ic = null;
		ConnectionFactory cf = null;
		Connection connection = null;

		try {
			ic = getInitialContext();

			cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
			Topic topic = (Topic) ic.lookup(destinationName);

			connection = cf.createConnection();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer publisher = session.createProducer(topic);
			MessageConsumer subscriber = session.createConsumer(topic);

			subscriber.setMessageListener(this);
			connection.start();

			TextMessage message = session.createTextMessage("Hello!");
			publisher.send(message);

			Scanner keyIn = new Scanner(System.in);

			System.out.print("JMS Server listening. Type a Key + CR to exit\n");
			keyIn.next();

		} finally {
			if (ic != null) {
				try {
					ic.close();
				} catch (Exception e) {
					throw e;
				}
			}

			closeConnection(connection);
		}
	}

	public synchronized void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		String strMessage = null;
		try {
			strMessage = text.getText();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println("Message received: " + strMessage);
	}

	private void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (JMSException jmse) {
			System.out.println("Could not close connection " + con + " exception was " + jmse);
		}
	}

	protected boolean isQueueExample() {
		return true;
	}

	public static void main(String[] args) throws Exception {
		new TopicExample().example();
	}

	public static Context getInitialContext() throws javax.naming.NamingException {

		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		p.put(Context.URL_PKG_PREFIXES, " org.jboss.naming:org.jnp.interfaces");
		p.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		return new javax.naming.InitialContext(p);
	}
}

