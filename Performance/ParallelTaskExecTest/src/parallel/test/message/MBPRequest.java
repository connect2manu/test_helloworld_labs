package parallel.test.message;

import parallel.test.task.Task;
import parallel.test.task.MBPTask;

public class MBPRequest extends Request {
	final REQUEST_TYPE reuquestType = REQUEST_TYPE.MBP_WS;
	
	private String requestId;

	public MBPRequest(String requestId) {
		this.requestId = requestId;
	}

	public REQUEST_TYPE getRequestType() {
		return reuquestType;
	}

	public Task createTask() {
		MBPTask task = new MBPTask();
		task.setRequest(this);
		return task;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((requestId == null) ? 0 : requestId.hashCode());
		result = prime * result
				+ ((reuquestType == null) ? 0 : reuquestType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MBPRequest other = (MBPRequest) obj;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (reuquestType != other.reuquestType)
			return false;
		return true;
	}

}