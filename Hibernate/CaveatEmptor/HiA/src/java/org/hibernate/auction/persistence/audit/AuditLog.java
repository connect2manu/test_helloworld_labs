package org.hibernate.auction.persistence.audit;

import net.sf.hibernate.*;

import java.io.Serializable;
import java.sql.Connection;

import org.hibernate.auction.persistence.HibernateUtil;
import org.hibernate.auction.model.Auditable;

/**
 * The audit log helper that logs actual events.
 * <p>
 * The <tt>logEvent()</tt> method needs a JDBC connection, it will
 * open a new Hibernate <tt>Session</tt> on that connection and
 * persist the event. The temporary <tt>Session</tt> is then closed,
 * transaction handling is left to the client calling this method.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class AuditLog {

	public static void logEvent(
		String message,
		Auditable entity,
		Long userId,
		Connection connection)
		throws CallbackException {

		Session tempSession =
		  HibernateUtil.getSessionFactory().openSession(connection);

		try {
			AuditLogRecord record =
				new AuditLogRecord(message,
								   entity.getId(),
								   entity.getClass(),
								   userId );

			tempSession.save(record);
			tempSession.flush();

		} catch (Exception ex) {
			throw new CallbackException(ex);

		} finally {
			try {
				tempSession.close();
			} catch (HibernateException ex) {
				throw new CallbackException(ex);
			}
		}
	}
}
