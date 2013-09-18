package org.hibernate.ce.auction.command;

import org.hibernate.ce.auction.persistence.HibernateUtil;


/**
 * The implementation of a generic EJB command handler.
 *
 * @author Christian Bauer <christian@hibernate.org>
 */
public class CommandHandlerBean {

	public Command executeCommand(Command command)
		throws CommandException {
        command.execute();
		return command;
	}
}
