package de.uni_due.s3.evaluator.exceptions.parserruntime;

/**
 * Is thrown when an expression can't be parsed
 * 
 * @author frichtscheid
 *
 */
public class ParserRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7059884607867146481L;

	public ParserRuntimeException(String message) {
		super(message);
	}

	public ParserRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
