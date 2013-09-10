import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class TestClass {

	static String path = "/";
	static String host = "localhost";
	static int port = 1001;
	static String targetUrl = "http://localhost:8080/triggerservice";

	public static void main(String[] args) throws UnknownHostException, IOException {
		createSocketRequest();
		// createSocketRequest2();
		// createHttpClientRequest();
	}

	private static void createSocketRequest2() throws UnsupportedEncodingException, IOException {
	    String data = URLEncoder.encode("key1", "UTF-8") + "=" + URLEncoder.encode("value1", "UTF-8");
		Socket socket = new Socket(host, port);
	    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
	    wr.write("POST " + path + " HTTP/1.0\r\n");
	    wr.write("Content-Length: " + data.length() + "\r\n");
	    wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
	    wr.write("\r\n");

	    wr.write(data);
	    wr.flush();

	    BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    String line;
	    while ((line = rd.readLine()) != null) {
	      System.out.println(line);
	    }
	    wr.close();
	    rd.close();		
	}

	private static void createHttpClientRequest() throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://localhost:1001");
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
	}

	private static void createSocketRequest() throws UnknownHostException, IOException {
		/*		Socket soc = new Socket("localhost", 1001);
				PrintWriter pw = new PrintWriter(soc.getOutputStream(), true);
				pw.println("GET " + path + " HTTP/1.1\r\n");
				pw.print("Host: " + host + "\r\n");
				pw.println("\r\n");
				pw.flush();

				BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				String output = null;
				while ((output = br.readLine()) != null) {
					System.out.println("Server Output: " + output);
				}

				pw.close();
				br.close();*/

		Socket soc = new Socket("localhost", 1002);
		PrintWriter pw = new PrintWriter(soc.getOutputStream());
		pw.println("GET / HTTP/1.1");
		pw.println("Host: localhost");
		pw.println();
		pw.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
		String t;
		while ((t = br.readLine()) != null) {
			System.out.println(t);
		}
		br.close();
	}

}
