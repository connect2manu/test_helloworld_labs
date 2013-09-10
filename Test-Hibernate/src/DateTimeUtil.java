import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author manu.mehrotra
 */
public class DateTimeUtil {

	public DateTimeUtil() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns $param.name$
	 * 
	 * @return
	 */
	public static String getCurrentTimestamp() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		String timeStamp = Integer.toString(calendar.get(Calendar.YEAR));
		int mm = calendar.get(Calendar.MONTH) + 1;
		int dd = calendar.get(Calendar.DATE);
		int hh = calendar.get(Calendar.HOUR_OF_DAY);
		int min = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		int ms = calendar.get(Calendar.MILLISECOND);

		if (mm < 10) {
			timeStamp += "0" + mm;
		} else {
			timeStamp += mm;
		}

		if (dd < 10) {
			timeStamp += "0" + dd;
		} else {
			timeStamp += dd;
		}

		if (hh < 10) {
			timeStamp += "0" + hh;
		} else {
			timeStamp += hh;
		}

		if (min < 10) {
			timeStamp += "0" + min;
		} else {
			timeStamp += min;
		}

		if (sec < 10) {
			timeStamp += "0" + sec;
		} else {
			timeStamp += sec;
		}

		if (ms >= 100) {
			timeStamp += ms;
		} else {

			if (ms >= 10) {
				timeStamp += "0" + ms;
			} else {
				timeStamp += "00" + ms;
			}
		}

		return timeStamp;
	} // end method getCurrentTimestamp

	/**
	 * @param tStamp
	 * @return
	 */
	public static String getStrFormatFromTimestamp(Timestamp tStamp) {
		SimpleDateFormat formatWithMillisecond = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		if (tStamp == null) {
			return null;
		}

		java.util.Date newDate = new java.util.Date(tStamp.getTime());

		return formatWithMillisecond.format(newDate);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("----------------------------------------");
		String calTimestamp = getCurrentTimestamp();
		System.out.println("getCurrentTimestamp = " + calTimestamp);
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(Long.parseLong(calTimestamp));
		System.out.println("java.sql.Timestamp from getCurrentTimestamp() = " + sqlTimestamp);
		System.out.println("java.sql.Timestamp.getTime() from getCurrentTimestamp() = " + sqlTimestamp.getTime());
		System.out.println("getStrFormatFromTimestamp() from getCurrentTimestamp() = "
				+ getStrFormatFromTimestamp(sqlTimestamp));
		System.out.println("------------------ System.currentTimeMillis() ----------------------");
		long sysCurrentTimeMillis = System.currentTimeMillis();
		System.out.println("System.currentTimeMillis() = " + sysCurrentTimeMillis);
		System.out.println("-------------------- java.util.Date --------------------");
		java.util.Date utilDate = new java.util.Date(sysCurrentTimeMillis);
		System.out.println("## java.util.Date  = " + utilDate);
		System.out.println("java.util.Date.getTime() = " + utilDate.getTime());
		System.out.println("--------------------- java.sql.Timestamp -------------------");
		java.sql.Timestamp sqlCurrentTimestamp = new java.sql.Timestamp(sysCurrentTimeMillis);
		System.out.println("java.sql.Timestamp = " + sqlCurrentTimestamp);
		System.out.println("java.sql.Timestamp.getTime() = " + sqlCurrentTimestamp.getTime());
		System.out.println("---------------------- java.sql.Timestamp from Date ------------------");
		java.sql.Timestamp sqlUtilDateTimestamp = new java.sql.Timestamp(new Date(sysCurrentTimeMillis).getTime());
		System.out.println("java.sql.Timestamp from java.util.Date = " + sqlUtilDateTimestamp);
		System.out.println("java.sql.Timestamp.getTime() from java.util.Date = " + sqlUtilDateTimestamp.getTime());
		System.out.println("--------------------------------------------------------------------");
		System.out.println("------------------- Truncating 13 digits millis to 10 digits ---------------------");

		System.out.println("sysCurrentTimeMillis = " + sysCurrentTimeMillis);
		String strMillis = sysCurrentTimeMillis + "";

		/*
		 * Truncating millis to 10 digits
		 */
		int originalStrMillisLen = strMillis.length();
		if (originalStrMillisLen > 10) {
			strMillis = strMillis.substring(0, strMillis.length() - (originalStrMillisLen - 10));
			sysCurrentTimeMillis = Long.parseLong(strMillis);
			System.out.println("TRUNCATED sysCurrentTimeMillis = " + sysCurrentTimeMillis);
		}

		System.out.println("sysCurrentTimeMillis (10 Digits) = " + sysCurrentTimeMillis);
		System.out.println("----------------------------------------");
		System.out.println("------------------ Time with 10 Digit Millis ----------------------");

		java.util.Date utilDate2 = new java.util.Date(sysCurrentTimeMillis * 1000L);
		System.out.println(">> java.util.Date  = " + utilDate2);
		System.out.println("java.util.Date.getTime() = " + utilDate2.getTime());
		System.out.println("----------------------------------------");
		java.sql.Timestamp sqlCurrentTimestamp2 = new java.sql.Timestamp(sysCurrentTimeMillis);
		System.out.println("java.sql.Timestamp = " + sqlCurrentTimestamp2);
		System.out.println("java.sql.Timestamp.getTime() = " + sqlCurrentTimestamp2.getTime());
		System.out.println("----------------------------------------");
		java.sql.Timestamp sqlUtilDateTimestamp2 = new java.sql.Timestamp(new Date(sysCurrentTimeMillis).getTime());
		System.out.println("java.sql.Timestamp from java.util.Date = " + sqlUtilDateTimestamp2);
		System.out.println("java.sql.Timestamp.getTime() from java.util.Date = " + sqlUtilDateTimestamp2.getTime());
		System.out.println("----------------------------------------------------------------------------");
		System.out
				.println("------------------ Time with 13 Digit Millis having trailing zeros @ the end  ----------------------");
		System.out.println("----------------------------------------------------------------------------");
		/*
		 * Adding trailing zeros to make it again 13 digits.
		 */
		if (strMillis.length() <= 13) {
			int offsetLen = 13 - strMillis.length();
			for (int offset = 0; offset < offsetLen; offset++) {
				strMillis = strMillis + "0";
			}
		}
		System.out.println("trailing zeros sysCurrentTimeMillis = " + strMillis);
		sysCurrentTimeMillis = Long.parseLong(strMillis);
		System.out.println("----------------------------------------------------------------------------");
		java.util.Date utilDate3 = new java.util.Date(sysCurrentTimeMillis);
		System.out.println("java.util.Date  = " + utilDate3);
		System.out.println("java.util.Date.getTime() = " + utilDate3.getTime());
		System.out.println("----------------------------------------");
		java.sql.Timestamp sqlCurrentTimestamp3 = new java.sql.Timestamp(sysCurrentTimeMillis);
		System.out.println("java.sql.Timestamp = " + sqlCurrentTimestamp3);
		System.out.println("java.sql.Timestamp.getTime() = " + sqlCurrentTimestamp3.getTime());
		System.out.println("----------------------------------------");
		java.sql.Timestamp sqlUtilDateTimestamp3 = new java.sql.Timestamp(new Date(sysCurrentTimeMillis).getTime());
		System.out.println("java.sql.Timestamp from java.util.Date = " + sqlUtilDateTimestamp3);
		System.out.println("java.sql.Timestamp.getTime() from java.util.Date = " + sqlUtilDateTimestamp3.getTime());
		System.out.println("----------------------------------------------------------------------------");
	}

}
