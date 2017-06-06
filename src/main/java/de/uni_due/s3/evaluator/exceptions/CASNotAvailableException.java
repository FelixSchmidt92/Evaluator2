package de.uni_due.s3.evaluator.exceptions;

public class CASNotAvailableException extends EvaluatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805641306828709703L;

	public CASNotAvailableException(String message) {
		super(message);
	}
	
	public CASNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

}
