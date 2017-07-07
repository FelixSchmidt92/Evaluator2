package de.uni_due.s3.evaluator.exceptions.cas;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

/**
 * This exception is the parent for exceptions which occur during operations
 * with a cas-system
 * 
 * @author frichtscheid
 *
 */
public abstract class CasException extends EvaluatorException {

	public CasException(String message) {
		super("CasException:" + message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
