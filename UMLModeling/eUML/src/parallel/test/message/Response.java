package parallel.test.message;

public abstract class Response {

	public RESPONSE_TYPE responseType;
	
	protected Exception exception;

	public enum RESPONSE_TYPE {
		MBP_WS, URH_EJB, UUSD_EJB, ERROR
	}

	public void setRequestType() {
		getResponseType();
	}

	public abstract RESPONSE_TYPE getResponseType();

	public Exception getException() {
		return exception;
	}

	public void setException(Exception e) {
		this.exception = e;
	}

}