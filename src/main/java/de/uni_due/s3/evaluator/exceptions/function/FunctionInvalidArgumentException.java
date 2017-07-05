package de.uni_due.s3.evaluator.exceptions.function;

import de.uni_due.s3.evaluator.core.function.Function;

/**
 * This exception should be thrown when a function gets the wrong arguments
 * @author frichtscheid
 *
 */
public class FunctionInvalidArgumentException extends FunctionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699421968544847893L;

	public FunctionInvalidArgumentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public FunctionInvalidArgumentException(Function function, String objType) {
		super("Wrong arguments in Function: "+function.getClass().getSimpleName()+". Argument should be of type "+objType	);
		}

}
