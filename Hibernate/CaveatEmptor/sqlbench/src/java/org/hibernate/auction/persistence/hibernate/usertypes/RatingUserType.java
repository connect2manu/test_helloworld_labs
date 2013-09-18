package org.hibernate.auction.persistence.hibernate.usertypes;

import net.sf.hibernate.*;
import org.hibernate.auction.model.Rating;

import java.sql.*;

/**
 * Hibernate custom mapping type for Rating.
 * <p>
 * This mapping type persists comment ratings to a <tt>VARCHAR</tt>
 * database column.
 *
 * @see Rating
 * @author Christian Bauer <christian@hibernate.org>
 */
public class RatingUserType implements UserType {

	private static final int[] SQL_TYPES = {Types.VARCHAR};

	public int[] sqlTypes() { return SQL_TYPES; }
	public Class returnedClass() { return Rating.class; }
	public boolean equals(Object x, Object y) { return x == y; }
	public Object deepCopy(Object value) { return value; }
	public boolean isMutable() { return false; }

	public Object nullSafeGet(ResultSet resultSet,
							  String[] names,
							  Object owner)
			throws HibernateException, SQLException {

	  String name = resultSet.getString(names[0]);
	  return resultSet.wasNull() ? null : Rating.getInstance(name);
	}

	public void nullSafeSet(PreparedStatement statement,
							Object value,
							int index)
			throws HibernateException, SQLException {

		if (value == null) {
			statement.setNull(index, Types.VARCHAR);
		} else {
			statement.setString(index, value.toString());
		}
	}
}