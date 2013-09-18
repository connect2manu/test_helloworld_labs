package parallel.test.pool;

import java.util.concurrent.atomic.AtomicInteger;

public class AuthThread extends Thread {

	public static final String NAME = "AUTH-Thread";
	private static final AtomicInteger created = new AtomicInteger();

	public AuthThread(Runnable r, String name) {
		super(r, name + "-" + created.incrementAndGet());
	}

	public void run() {
		super.run();
	}

}