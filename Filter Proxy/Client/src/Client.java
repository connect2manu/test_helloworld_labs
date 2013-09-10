import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Test client.
 */
public class Client {

	/**
	 * @param args
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket soc = new Socket("localhost", 1000);
		PrintWriter pw = new PrintWriter(soc.getOutputStream());
		pw.println("GET / HTTP/1.1");
		pw.print("Host: localhost");
		pw.print("");
		pw.print("\r\n");
		pw.print("\n\n");
		pw.flush();

		BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		String t;
		while ((t = br.readLine()) != null) {
			System.out.println(t);
		}
		br.close();
	}
}
