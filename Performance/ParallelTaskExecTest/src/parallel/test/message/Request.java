package parallel.test.message;

import parallel.test.task.Task;

public abstract class Request {
	
	protected int timeOutInSeconds = Integer.MAX_VALUE;
	
	public enum REQUEST_TYPE {
		MBP_WS, URH_EJB, UUSD_EJB
	}
	
	public REQUEST_TYPE requestType;

	public int getTimeOutInSeconds() {
		return timeOutInSeconds;
	}

	public void setTimeOutInSeconds(int timeOutInSeconds) {
		this.timeOutInSeconds = timeOutInSeconds;
	}

	public abstract REQUEST_TYPE getRequestType();

	public abstract Task createTask();

	public abstract boolean equals(Object otherRequest);

	public abstract int hashCode();
}