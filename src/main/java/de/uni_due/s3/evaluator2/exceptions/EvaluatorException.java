package de.uni_due.s3.evaluator2.exceptions;

/**
 * Exceptions in evaluator package should inherit from EvaluatorException.
 * 
 * @author spobel
 *
 */
public class EvaluatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4541164827328210954L;
	
	public EvaluatorException(String message) {
		super(message);
	}
	
	public EvaluatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
