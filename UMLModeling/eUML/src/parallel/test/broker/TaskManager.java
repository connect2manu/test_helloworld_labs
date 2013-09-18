package parallel.test.broker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import parallel.test.message.URHRequest;
import parallel.test.message.URHResponse;
import parallel.test.message.ErrorResponse;
import parallel.test.message.Request;
import parallel.test.message.Response;
import parallel.test.message.MBPRequest;
import parallel.test.message.MBPResponse;
import parallel.test.message.UUSDRequest;
import parallel.test.pool.AuthThreadFactory;
import parallel.test.pool.AuthThreadPoolExecutor;
import parallel.test.task.Task;

public class TaskManager {

	private static ThreadPoolExecutor webServiceExecutor;
	private static ThreadPoolExecutor ejbExecutor;

	private static int CORE_POOL_SIZE = 10;
	private static int MAX_POOL_SIZE = 15;
	private static int KEEP_ALIVE_TIME = 10;
	private static int BLOCKING_QUEUE_CPACITY = 10;

	public void init() {
		webServiceExecutor = new AuthThreadPoolExecutor(
									CORE_POOL_SIZE,
									MAX_POOL_SIZE, 
									KEEP_ALIVE_TIME, 
									TimeUnit.SECONDS,
									new PriorityBlockingQueue<Runnable>(BLOCKING_QUEUE_CPACITY),
									new AuthThreadFactory("WSThreadPool"));

		ejbExecutor = new AuthThreadPoolExecutor(
									CORE_POOL_SIZE, 
									MAX_POOL_SIZE,
									KEEP_ALIVE_TIME, 
									TimeUnit.SECONDS,
									new PriorityBlockingQueue<Runnable>(BLOCKING_QUEUE_CPACITY),
									new AuthThreadFactory("EJBThreadPool"));
	}
	
	public static void shutdown() {
		webServiceExecutor.shutdownNow();
		ejbExecutor.shutdownNow();
	}

	public static Map<Request, Response> processRequest(List<Request> requests) {
		if (requests == null || requests.isEmpty()) {
			throw new IllegalArgumentException("request is null or empty.");
		}

		Iterator<Request> requestItr = requests.iterator();
		List<Task> taskList = new ArrayList<Task>();

		while (requestItr.hasNext()) {
			Request request = requestItr.next();
			Task task = request.createTask();
			taskList.add(task);
		}

		TaskManager parallelTaskManager = new TaskManager();
		parallelTaskManager.init();

		return parallelTaskManager.processInParallel(taskList);
	}

	public Map<Request, Response> processInParallel(List<Task> tasks) {
		Map<Request, Response> responseMap = new HashMap<Request, Response>();
		
		Map<Request, Future<Response>> returnMap = Collections
				.synchronizedMap(new HashMap<Request, Future<Response>>());
		Iterator<Task> taskItr = tasks.iterator();
		while (taskItr.hasNext()) {
			Task task = taskItr.next();
			Request req = task.getRequest();
			Request.REQUEST_TYPE rtype = req.getRequestType();
			ThreadPoolExecutor threadPoolExecutor = getExecutor(rtype);

			if (threadPoolExecutor == null) {
				throw new RuntimeException(
						"Could not get proper ThreadPoolExecutor, aborting the tasks..");
			}

			// submit(task) will return Future
			returnMap.put(req, threadPoolExecutor.submit(task));
		}

		List<Request> requestList = new ArrayList<Request>(returnMap.keySet());
		Request request = null;
		Future<Response> futureResponse = null;
		for (Request request1 : requestList) {
			request = request1;
			futureResponse = returnMap.get(request);
			try {
				// get with timeout on Future
				responseMap.put(request, futureResponse.get(
						request.getTimeOutInSeconds(), TimeUnit.SECONDS));

			} catch (TimeoutException e) {
				createErrorResponse(responseMap, request, e);
			} catch (ExecutionException e) {
				createErrorResponse(responseMap, request, e);
			} catch (InterruptedException e) {
				createErrorResponse(responseMap, request, e);
			} catch (CancellationException e) {
				createErrorResponse(responseMap, request, e);
				// cancel all other task if one fails (optional)
				for (Request req : requestList) {
					returnMap.get(req).cancel(true);
				}
			} finally {
				futureResponse.cancel(true);
			}
		}
		return responseMap;
	}

	private void createErrorResponse(Map<Request, Response> responseMap,
			Request request, Exception e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setException(e);
		responseMap.put(request, errorResponse);
	}

	private ThreadPoolExecutor getExecutor(Request.REQUEST_TYPE requestType) {
		switch (requestType) {
		case URH_EJB:
			return ejbExecutor;
		case MBP_WS:
			return webServiceExecutor;
		case UUSD_EJB:
			return ejbExecutor;
		}
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]) {
		Request urhRequest = new URHRequest("URH_RequestId");
		Request uusdRequest = new UUSDRequest("UUSD_RequestId");
		Request mbpRequest = new MBPRequest("MBP_RequestId");
		

		List<Request> requests = new ArrayList<Request>();
		requests.add(urhRequest);
		requests.add(uusdRequest);
		requests.add(mbpRequest);
		
		Map<Request, Response> aggregateResponseMap = processRequest(requests);
		System.out.println(">> aggregateResponseMap = "+aggregateResponseMap);

		Response response = null;
		for (Request request : aggregateResponseMap.keySet()) {
			response = aggregateResponseMap.get(request);
			
			switch (response.getResponseType()) {
			case URH_EJB:
				List<String> dbResAddresses = ((URHResponse)response).getAllAddresses();
				System.out.println("+ inside TaskManager | main() > dbResAddresses = ["+request.getRequestType()+","+response.getResponseType() +", "+dbResAddresses+"]");
				break;
			case MBP_WS:
				List<String> wsResAddresses = ((MBPResponse)response).getAllAddresses();
				System.out.println("+ inside TaskManager | main() > wsResAddresses = ["+request.getRequestType()+","+response.getResponseType() +", "+wsResAddresses+"]");
				break;
			case UUSD_EJB:
				break;
			case ERROR:
				Exception excep = ((ErrorResponse)response).getException();
				System.out.println("+ inside TaskManager | main() > excep = ["+request.getRequestType()+","+response.getResponseType() +", "+excep.getMessage()+"]");
				break;
			default:
				System.out.println("+ inside TaskManager | main() > INVALID ResponseType = "+request.requestType+","+response.getResponseType());
			}
		}
		
		shutdown();
		// decorate aggregated response.
	}

}