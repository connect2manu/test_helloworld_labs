package parallel.test.pool;

import java.util.concurrent.ThreadFactory;

public class AuthThreadFactory implements ThreadFactory {
	private final String poolName;

	public AuthThreadFactory(String poolName) {
		this.poolName = poolName;
	}

	public Thread newThread(Runnable r) {
		return new AuthThread(r, poolName);
	}
}