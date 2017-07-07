package de.uni_due.s3.evaluator.exceptions.function;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

/**
 * This is the father for all exceptions which have to do with the functions
 */
public class FunctionException extends EvaluatorException {

	private static final long serialVersionUID = 4016801474266206443L;

	public FunctionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FunctionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
