package loadTriggerTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class URLConnectionReader {
	public static void main(String[] args) throws Exception {

		System.out.println("Main thread starting.");
		MyThread mt = new MyThread();
		Thread newThrd = new Thread(mt, "T1");
		newThrd.start();

		MyThread mt1 = new MyThread();
		Thread newThrd1 = new Thread(mt1, "T2");
		newThrd1.start();

		MyThread mt2 = new MyThread();
		Thread newThrd2 = new Thread(mt2, "T3");
		newThrd2.start();

		MyThread mt3 = new MyThread();
		Thread newThrd3 = new Thread(mt3, "T4");
		newThrd3.start();

		MyThread mt4 = new MyThread();
		Thread newThrd4 = new Thread(mt4, "T5");
		newThrd4.start();

		MyThread mt5 = new MyThread();
		Thread newThrd5 = new Thread(mt5, "T6");
		newThrd5.start();

		MyThread mt6 = new MyThread();
		Thread newThrd6 = new Thread(mt6, "T7");
		newThrd6.start();
	}
}

class MyThread implements Runnable {
	// 0014F8E3537D
	String writeTrigger = "http://10.0.245.50:8080/triggerservice/writeTrigger?TriggerType=T_NPVR&TimeStamp=";
	String getTriggers = "http://10.0.245.50:8080/triggerservice/getTriggers?MACAddress=";
	int count;
	boolean flag;
	URL url = null;
	URLConnection conn = null;

	String str = "";

	MyThread() {
		count = 0;
	}

	@Override
	public void run() {
		System.out.println("MyThread starting.");
		try {
			do {
				// Thread.sleep(10);
				System.out.println("In MyThread - " + Thread.currentThread().getName() + ", count is " + count);
				synchronized (str) {
					count++;
					if (flag) {
						ObjectInputStream objInStream = null;
						try {
							url = new URL(getTriggers + (count - 1));
							conn = url.openConnection();
							conn.connect();
							// BufferedReader in = new BufferedReader(new
							// InputStreamReader(conn.getInputStream()));
							/*String inputLine;
							while ((inputLine = in.readLine()) != null) 
							    System.out.println(inputLine);
							in.close();*/

						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						ObjectInputStream objInStream = null;
						try {
							url = new URL(writeTrigger + new Date().getTime() + "&MACAddress=" + count);
							conn = url.openConnection();
							conn.connect();
							// BufferedReader in = new BufferedReader(new
							// InputStreamReader(conn.getInputStream()));
							/*String inputLine;
							while ((inputLine = in.readLine()) != null) 
							    System.out.println(inputLine);
							in.close();*/
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							// conn.disconnect();
						}
					}
					flag = !flag;
				}
			} while (count < 100000);
		} catch (Exception exc) {
			System.out.println("MyThread interrupted.");
		}
		System.out.println("MyThread terminating.");
	}

	public synchronized void callTQS() {

	}

}
