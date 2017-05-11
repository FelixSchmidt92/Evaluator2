package de.uni_due.s3.evaluator.exceptions;

public class ParserException extends EvaluatorException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7059884607867146481L;

	public ParserException(String message) {
		super(message);
	}
	
	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
