package parallel.test.task;

import java.util.concurrent.Callable;

import parallel.test.message.Request;
import parallel.test.message.Response;

public abstract class Task implements Callable<Response> {

	public abstract void setConectionParam(String connUrl, String userName,
			String password);

	public abstract void setRequest(Request request);

	public abstract Request getRequest();

	public abstract Response call() throws Exception;

}