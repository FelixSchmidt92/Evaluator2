package de.uni_due.s3.evaluator.exceptions;

public class NoFunctionToCASException extends EvaluatorException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5373678236257372906L;

	public NoFunctionToCASException(String message) {
		super(message);
	}
	
	public NoFunctionToCASException(String message, Throwable cause) {
		super(message, cause);
	}

}
