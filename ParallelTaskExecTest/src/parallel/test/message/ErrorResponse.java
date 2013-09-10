package parallel.test.message;

public class ErrorResponse extends Response {

	public ErrorResponse() {
	}

	public ErrorResponse(Exception s) {
		this.exception = s;
	}

	public RESPONSE_TYPE getResponseType() {
		return RESPONSE_TYPE.ERROR;
	}
}