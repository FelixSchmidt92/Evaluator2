package de.uni_due.s3.evaluator.exceptions.function;

import de.uni_due.s3.evaluator.core.function.Function;

/**
 * This exception is thrown when a function gets to much or to few arguments
 * 
 * @author frichtscheid
 *
 */
public class FunctionInvalidNumberOfArgumentsException extends FunctionException {

	public FunctionInvalidNumberOfArgumentsException(Function function, int minArgs, int maxArgs,
			int actualArgumentSize, String message) {
		super("Function: " + function.getClass().getSimpleName() + " got " + actualArgumentSize
				+ " but needs Arguments between " + minArgs + " and " + maxArgs + ". " + message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 6685389413796962608L;

}
