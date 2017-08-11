package de.uni_due.s3.evaluator2.exceptions.function;

import de.uni_due.s3.evaluator2.core.function.Function;

public class FunctionInvalidArgumentTypeException extends FunctionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374611801914313850L;

	public FunctionInvalidArgumentTypeException(String message) {
		super(message);
	}
	
	public FunctionInvalidArgumentTypeException(Function function, String types) {
		super("Wrong arguments in Function: " + function.getClass().getSimpleName() + ". Arguments needs to be: " + types);
	}

}
