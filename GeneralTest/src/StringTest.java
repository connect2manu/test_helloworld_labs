
public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] a) {
		String contentTypes = "N, CS";

		StringBuilder recordTypes = new StringBuilder();
		String[] types = contentTypes.split(",");
		System.out.println("Splitted Types Length: " + types.length);
		for (int i = 0; i < types.length; i++) {
			recordTypes.append("'");
			recordTypes.append(types[i].trim());
			recordTypes.append("'");
			System.out.println("i=" + i + ", types.length - 1 =" + (types.length - 1));
			if (i < types.length - 1) {
				recordTypes.append(",");
			}
		}
		System.out.println("recordTypes: " + recordTypes.toString());
	}

}
