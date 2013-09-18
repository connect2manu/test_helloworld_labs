package parallel.test.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class AuthThreadPoolExecutor extends ThreadPoolExecutor {

	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final AtomicLong totalTime = new AtomicLong();
	private final AtomicLong numberOfTasks = new AtomicLong();

	public AuthThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public AuthThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
			ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
	}

	public AuthThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
			RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				handler);
	}

	public AuthThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,
			ThreadFactory threadFactory, RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
		System.out.println("WSThreadPoolExecutor init....");

	}

	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		System.out.println("Thread :" + t + " start " + r);
		startTime.set(System.nanoTime());
	}

	protected void afterExecution(Runnable r, Throwable t) {
		try {
			long endTime = System.nanoTime();
			long taskTime = endTime - startTime.get();
			numberOfTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			System.out.println("Thread :" + t + " end " + r
					+ " , total time taken=" + taskTime);
		} finally {
			super.afterExecute(r, t);
		}
	}

	protected void terminated() {
		try {
//			System.out.println("Terminated: average time="
//					+ (totalTime.get() / numberOfTasks.get()));
		} finally {
			super.terminated();
		}
	}
}