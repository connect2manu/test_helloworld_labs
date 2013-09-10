package org.hibernate.ce.auction.persistence.audit;

import org.hibernate.*;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.*;

import org.hibernate.ce.auction.model.*;
import org.apache.commons.logging.*;

public class AuditLogInterceptor extends EmptyInterceptor {

	private static Log log = LogFactory.getLog(AuditLogInterceptor.class);

	private Session session;
	private Long userId;

	private Set inserts = new HashSet();
	private Set updates = new HashSet();

	public void setSession(Session session) {
		this.session=session;
	}
	public void setUserId(Long userId) {
		this.userId=userId;
	}

	public boolean onSave(Object entity,
						 Serializable id,
						 Object[] state,
						 String[] propertyNames,
						 Type[] types)
			throws CallbackException {

		if (entity instanceof Auditable)
			inserts.add(entity);

		return false;
	}

	public boolean onFlushDirty(Object entity,
								Serializable id,
								Object[] currentState,
								Object[] previousState,
								String[] propertyNames,
								Type[] types)
			throws CallbackException {
		if (entity instanceof Auditable)
			updates.add(entity);

		return false;
	}

	public void postFlush(Iterator iterator) throws CallbackException {
		try {
			for (Iterator it = inserts.iterator(); it.hasNext();) {
				Auditable entity = (Auditable) it.next();
				log.debug("Intercepted creation of : " + entity);
				AuditLog.logEvent("create",
								  entity,
								  userId,
								  session.connection());
			}
			for (Iterator it = updates.iterator(); it.hasNext();) {
				Auditable entity = (Auditable) it.next();
				log.debug("Intercepted modification of : " + entity);
				AuditLog.logEvent("update",
								  entity,
								  userId,
								  session.connection());
			}
		} catch (HibernateException ex) {
			throw new CallbackException(ex);
		} finally {
			inserts.clear();
			updates.clear();
		}
	}

}
