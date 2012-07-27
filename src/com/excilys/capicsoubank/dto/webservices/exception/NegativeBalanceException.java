package com.excilys.capicsoubank.dto.webservices.exception;

/**
 * Le compte est à découvert.
 * 
 * @author Nicolas Poirier
 */
public class NegativeBalanceException extends Exception {

	private static final long serialVersionUID = -8295808754003057528L;

	public NegativeBalanceException(Throwable throwable) {
		super(throwable);
	}
}
