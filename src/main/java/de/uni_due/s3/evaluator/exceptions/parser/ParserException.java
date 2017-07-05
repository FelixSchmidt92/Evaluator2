package de.uni_due.s3.evaluator.exceptions.parser;


/**
 * Is thrown when an expression can't be parsed
 * @author frichtscheid
 *
 */
public class ParserException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7059884607867146481L;

	public ParserException(String message) {
		super(message);
	}
	
	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
