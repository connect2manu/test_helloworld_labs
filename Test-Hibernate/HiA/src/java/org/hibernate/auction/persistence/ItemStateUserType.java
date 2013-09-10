package org.hibernate.auction.persistence;

import net.sf.hibernate.*;
import org.hibernate.auction.model.*;

import java.sql.*;

/**
 * Hibernate custom mapping type for ItemState.
 * <p>
 * This mapping type persists item state to a <tt>VARCHAR</tt>
 * database column.
 *
 * @see org.hibernate.auction.model.ItemState
 * @author Christian Bauer <christian@hibernate.org>
 */
public class ItemStateUserType implements UserType {

	private static final int[] SQL_TYPES = {Types.CHAR};

	public int[] sqlTypes() { return SQL_TYPES; }
	public Class returnedClass() { return ItemState.class; }
	public boolean equals(Object x, Object y) { return x == y; }
	public Object deepCopy(Object value) { return value; }
	public boolean isMutable() { return false; }

	public Object nullSafeGet(ResultSet resultSet,
							  String[] names,
							  Object owner)
			throws HibernateException, SQLException {

	  String name = resultSet.getString(names[0]);
	  return resultSet.wasNull() ?
				null :
				ItemState.getInstance(name.charAt(0));
	}

	public void nullSafeSet(PreparedStatement statement,
							Object value,
							int index)
			throws HibernateException, SQLException {

		if (value == null) {
			statement.setNull(index, Types.CHAR);
		} else {
			statement.setString(index, value.toString());
		}
	}
}