package de.uni_due.s3.evaluator.exceptions;

/**
 * Exceptions in evaluator package should inherit from EvaluatorException.
 * 
 * @author spobel
 *
 */
public class EvaluatorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4541164827328210954L;
	
	EvaluatorException(String message) {
		super(message);
	}
	
	EvaluatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
