package com.manu.demo.application.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.manu.demo.application.al.BasePersistentObject;

@Entity
@Table(name = "NOTIFICATION")
// @Cacheable(value=true)
@Cache(usage=CacheConcurrencyStrategy.TRANSACTIONAL)
public class Notification implements BasePersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigInteger macaddress;
	private Date reminder;
	private Date message;

	public Notification() {
	}

	public Notification(BigInteger macaddress, Date reminder, Date message) {
		this.macaddress = macaddress;
		this.reminder = reminder;
		this.message = message;
	}
	
	@Id
	@Column(name="MACADDRESS")
	public BigInteger getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(BigInteger macaddress) {
		this.macaddress = macaddress;
	}

	@Column(name = "REMINDER", nullable = true)
	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminders) {
		reminder = reminders;
	}

	@Column(name = "MESSAGE", nullable = true)
	public Date getMessage() {
		return message;
	}

	public void setMessage(Date messageInfo) {
		message = messageInfo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notification [bigMacaddress=");
		builder.append(macaddress);
		builder.append(", reminder=");
		builder.append(reminder);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (macaddress == null ? 0 : macaddress.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Notification other = (Notification) obj;
		if (macaddress == null) {
			if (other.macaddress != null) {
				return false;
			}
		} else if (!macaddress.equals(other.macaddress)) {
			return false;
		}
		return true;
	}

}
