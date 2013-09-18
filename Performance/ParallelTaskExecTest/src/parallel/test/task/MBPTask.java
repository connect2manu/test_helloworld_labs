package parallel.test.task;

import java.util.ArrayList;
import java.util.List;

import parallel.test.message.Request;
import parallel.test.message.Response;
import parallel.test.message.MBPRequest;
import parallel.test.message.MBPResponse;
import parallel.test.task.exception.TaskExecutionException;

public class MBPTask extends Task {

	private MBPRequest request;
	private String endPointUrl = null;
	private String user = null;
	private String pass = null;

	public void setConectionParam(String endPointURL, String user, String pass) {
		this.endPointUrl = endPointURL;
		this.user = user;
		this.pass = pass;
	}

	public void setRequest(Request request) {
		this.request = (MBPRequest) request;
	}

	public Request getRequest() {
		return request;
	}

	public Response call() throws TaskExecutionException {
		MBPResponse response = new MBPResponse();
		try {
			// call web service
			// get response
			// fake response for now
			
//			throw new IllegalStateException("Authentication invalid !!!");
			
			List<String> all = new ArrayList<String>();
			all.add("Manu at MBP");
			response.setAllAddresses(all);
			return response;
		} catch (Exception e) {
			TaskExecutionException taskExecutionException = new TaskExecutionException(
					e.getMessage());
			taskExecutionException.initCause(e);
			throw taskExecutionException;
		}

	}
}