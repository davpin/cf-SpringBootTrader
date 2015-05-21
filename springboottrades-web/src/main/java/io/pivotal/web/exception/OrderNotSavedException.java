package io.pivotal.web.exception;

public class OrderNotSavedException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269912483691692397L;

	public OrderNotSavedException(String message) {
		super(message);
	}

}
