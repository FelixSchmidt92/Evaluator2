package de.uni_due.s3.evaluator.exceptions.function;

import de.uni_due.s3.evaluator.core.function.Function;

public class FunctionInvalidArgumentTypeException extends FunctionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374611801914313850L;

	public FunctionInvalidArgumentTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public FunctionInvalidArgumentTypeException(Function function, String types) {
		super("Wrong arguments in Function: " + function.getClass().getSimpleName() + ". Arguments needs to be: " + types);
	}

}
