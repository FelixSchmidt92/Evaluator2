package de.uni_due.s3.evaluator2.exceptions.function;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;

/**
 * This is the father for all exceptions which have to do with the functions
 */
public class FunctionException extends EvaluatorException {

	private static final long serialVersionUID = 4016801474266206443L;

	public FunctionException(String message) {
		super(message);
	}

	public FunctionException(String message, Throwable cause) {
		super(message, cause);
	}

}
