package parallel.test.task.exception;

public class TaskExecutionException extends Exception {

	private static final long serialVersionUID = 1L;

	public TaskExecutionException() {
	}

	public TaskExecutionException(String message) {
		super(message);
	}

	public TaskExecutionException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public TaskExecutionException(Throwable throwable) {
		super(throwable);
	}

}