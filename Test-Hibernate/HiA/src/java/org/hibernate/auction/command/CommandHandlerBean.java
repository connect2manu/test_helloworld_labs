package org.hibernate.auction.command;

import org.hibernate.auction.persistence.HibernateUtil;

import javax.ejb.*;
import java.rmi.RemoteException;

/**
 * The implementation of a generic EJB command handler.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class CommandHandlerBean
	implements SessionBean, CommandHandler{

	public void setSessionContext(SessionContext sessionContext)
					throws EJBException, RemoteException {}
	public void ejbRemove()
					throws EJBException, RemoteException {}
	public void ejbActivate()
					throws EJBException, RemoteException {}
	public void ejbPassivate()
					throws EJBException, RemoteException {}

	public Command executeCommand(Command command)
		throws RemoteException, CommandException {

		try {
			command.execute();
		} catch (CommandException ex) {
			// Actually, set the UserTransaction in JTA to rollback only.
			// It is possible to not catch the exception and let the
			// container set rollback when this method fails.
			HibernateUtil.rollbackTransaction();
			throw ex;
		}
		return command;
	}
}
