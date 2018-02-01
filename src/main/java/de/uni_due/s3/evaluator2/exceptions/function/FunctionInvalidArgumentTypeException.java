package de.uni_due.s3.evaluator2.exceptions.function;

import de.uni_due.s3.evaluator2.function.AbstractFunction;

public class FunctionInvalidArgumentTypeException extends FunctionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374611801914313850L;

	public FunctionInvalidArgumentTypeException(String message) {
		super(message);
	}
	
	public FunctionInvalidArgumentTypeException(AbstractFunction function, String types) {
		super("Wrong argument in Function: " + function.getClass().getSimpleName() + ". Argument needs to be: " + types);
	}

}
