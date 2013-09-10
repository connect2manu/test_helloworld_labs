

/*
 * public class Test {
 * 
 * public static void main(String[] args) { // TODO Auto-generated method stub
 * String msg = "00AD546678e3"; BigInteger bi = new BigInteger(msg.getBytes());
 * System.out.println(bi); System.out.println(new String(bi.toByteArray()));
 * 
 * // Collections.copy(arg0, arg1); } }
 */

public class Test {

	/**
	 * @param args
	 */
	@SuppressWarnings("finally")
	public static void main(String[] args) {
		try {
			System.out.println("1");
			throw new IllegalStateException("IllegalState");
		} catch (IllegalStateException ise) {
			System.out.println("2");
			return;
		} catch (Exception e) {
			System.out.println("3");
			return;
		} finally {
			System.out.println("4");
			return;
		}

	}

}