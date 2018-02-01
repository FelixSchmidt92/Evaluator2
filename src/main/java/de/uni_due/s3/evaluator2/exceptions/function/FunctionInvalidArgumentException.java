package de.uni_due.s3.evaluator2.exceptions.function;

import de.uni_due.s3.evaluator2.function.AbstractFunction;

/**
 * This exception should be thrown when a function gets the wrong arguments
 * 
 * @author frichtscheid
 *
 */
public class FunctionInvalidArgumentException extends FunctionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699421968544847893L;

	public FunctionInvalidArgumentException(AbstractFunction function, String message) {
		super("Wrong arguments in Function: " + function.getClass().getSimpleName() + ". " + message);
	}

}
