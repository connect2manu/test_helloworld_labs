import java.math.BigInteger;

public class BigIntConversions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String macAddress = "0014F8E3537D";
		System.out.println("MAC in Bytes = " + macAddress.getBytes());

		/********************** Approach-1 *********************************/

		BigInteger bi = new BigInteger(macAddress.getBytes());
		System.out.println("BI=" + bi);

		String mac1 = new String(bi.toByteArray());
		System.out.println("MAC1=" + mac1);

		/*********************** Approach-2 ********************************/

		BigInteger bi2 = new BigInteger(macAddress, 16);
		System.out.println("BI2=" + bi2);
		String str = bi2.toString(16);

		System.out.println("MAC2 BEFORE =" + str);
		int lenght = str.length();
		if (lenght < 12) {
			int offest = 12 - lenght;
			for (int i = 1; i <= offest; i++) {
				str = "0" + str;
			}
		}
		System.out.println("MAC2 AFTER =" + str.toUpperCase());

		/*************************** OUTPUT ********************************/
		/*******************************************************************/
	}

	/*
	 * 
	 */

}
