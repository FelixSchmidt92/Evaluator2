package de.uni_due.s3.evaluator.exceptions.function;

import de.uni_due.s3.evaluator.core.function.Function;

public class InvalidResultTypeException extends FunctionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5039985494952072908L;

	public InvalidResultTypeException(Function function, String types) {
		super("Wrong result type in Function: " + function.getClass().getSimpleName() + ". Result has to be: " + types);
	}

}
