package de.uni_due.s3.evaluator2.exceptions.function;

import de.uni_due.s3.evaluator2.function.Function;

/**
 * This exception is thrown when a function gets to much or to few arguments
 * 
 * @author frichtscheid
 *
 */
public class FunctionInvalidNumberOfArgumentsException extends FunctionException {

	public FunctionInvalidNumberOfArgumentsException(Function function, String message) {
		super("Function: " + function.getClass().getSimpleName() + message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6685389413796962608L;

}
