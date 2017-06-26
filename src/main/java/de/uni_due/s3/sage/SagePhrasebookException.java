package de.uni_due.s3.sage;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

public class SagePhrasebookException extends EvaluatorException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2885130141045067955L;
	
	public SagePhrasebookException(String message) {
		super(message);
	}
	
	public SagePhrasebookException(String message, Throwable cause) {
		super(message, cause);
	}

}
