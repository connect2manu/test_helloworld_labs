
public class Manu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("test == " + test(0, true));

		boolean x = test(0, true);
		if (!x) {
			System.out.println("ajit");
		}
	}

	private static boolean test(int i, boolean exit) {
		boolean b = false;
		if (exit) {
			test(1, false);
		}

		if (i > 0) {
			b = true;
		}
		System.out.println("bbbb==" + b);


		return b;

	}

}
