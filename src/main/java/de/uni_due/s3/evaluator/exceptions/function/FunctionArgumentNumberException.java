package de.uni_due.s3.evaluator.exceptions.function;

import de.uni_due.s3.evaluator.core.function.Function;

/**
 * This exception is thrown when a function gets to much or to few arguments
 * @author frichtscheid
 *
 */
public class FunctionArgumentNumberException extends FunctionException{

	public FunctionArgumentNumberException(String message) {
		super(message);
	}
	
	public FunctionArgumentNumberException(Function function, int minArgs,int maxArgs,int actualArgumentSize){
		super("Function: "+function.getClass().getSimpleName()+"got "+actualArgumentSize+"but need Arguments between "+minArgs+","+maxArgs);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6685389413796962608L;



}
