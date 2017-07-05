package de.uni_due.s3.evaluator.exceptions.cas;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

/**
 * This is thrown when their is no connection to the specified cas!
 * @author frichtscheid
 *
 */
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
