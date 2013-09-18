package parallel.test.message;

import parallel.test.task.URHTask;
import parallel.test.task.Task;

public class URHRequest extends Request {
	
	final REQUEST_TYPE reuquestType = REQUEST_TYPE.URH_EJB;
	
	private String requestId;

	public URHRequest(String requestId) {
		this.requestId = requestId;
	}

	public REQUEST_TYPE getRequestType() {
		return reuquestType;
	}

	public Task createTask() {
		URHTask task = new URHTask();
		task.setRequest(this);
		return task;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		URHRequest other = (URHRequest) obj;
		if (requestId == null) {
			if (other.requestId != null)
				return false;
		} else if (!requestId.equals(other.requestId))
			return false;
		if (reuquestType != other.reuquestType)
			return false;
		return true;
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

}