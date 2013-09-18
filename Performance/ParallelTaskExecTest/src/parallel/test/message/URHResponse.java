package parallel.test.message;

import java.util.List;

public class URHResponse extends Response {
	private List<String> allAddresses;

	public RESPONSE_TYPE getResponseType() {
		return RESPONSE_TYPE.URH_EJB;
	}

	public List<String> getAllAddresses() {
		return allAddresses;
	}

	public void setAllAddresses(List<String> allAddresses) {
		this.allAddresses = allAddresses;
	}

}