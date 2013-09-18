package parallel.test.task;

import java.util.ArrayList;
import java.util.List;

import parallel.test.message.URHRequest;
import parallel.test.message.URHResponse;
import parallel.test.message.Request;
import parallel.test.message.Response;
import parallel.test.message.UUSDRequest;
import parallel.test.message.UUSDResponse;
import parallel.test.task.exception.TaskExecutionException;

public class UUSDTask extends Task {

	private UUSDRequest request;
	private String dbUrl;
	private String user;
	private String pass;

	public void setConectionParam(String dbURL, String user, String pass) {
		this.dbUrl = dbURL;
		this.user = user;
		this.pass = pass;
	}

	public void setRequest(Request request) {
		this.request = (UUSDRequest) request;
	}

	public Request getRequest() {
		return request;
	}

	public Response call() throws TaskExecutionException {
		UUSDResponse response = new UUSDResponse();
		try {
			// call DB
			// get response
			// fake response for now
			List all = new ArrayList();
			all.add("Manu at UUSD");
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