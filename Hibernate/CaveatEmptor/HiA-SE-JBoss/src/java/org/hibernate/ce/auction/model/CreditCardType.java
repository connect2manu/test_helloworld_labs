package org.hibernate.ce.auction.model;

public enum CreditCardType {

	MASTERCARD("Mastercard"),
    VISA("Visa"),
    AMEX("American Express");

    private final String debugName;

	private CreditCardType(String debugName) {
		this.debugName = debugName;
	}

	public String toString() {
	    return debugName;
	}

	// ********************** Business Methods ********************** //

	public boolean isValid(CreditCard cc) {
		// TODO: Implement syntactical validation of credit card information.
		return true;
	}

}