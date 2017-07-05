package de.uni_due.s3.evaluator.exceptions.cas;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

public class CasNotAvailableException extends EvaluatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805641306828709703L;

	public CasNotAvailableException(String message) {
		super(message);
	}
	
	public CasNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

}
