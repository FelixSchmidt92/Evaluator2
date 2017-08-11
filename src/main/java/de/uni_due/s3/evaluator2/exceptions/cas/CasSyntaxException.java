package de.uni_due.s3.evaluator2.exceptions.cas;

/**
 * This exception should be thrown when the syntax of a cas-specific command is wrong
 * @author frichtscheid
 *
 */
public class CasSyntaxException extends CasException{

	public CasSyntaxException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
