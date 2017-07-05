package de.uni_due.s3.evaluator.exceptions.representation;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

/**
 * This class is the parent of other exceptions which will be thrown when there is no specific OpenMath representation for the specified system implemented
 * @author frichtscheid
 *
 */
public abstract class NoRepresentationAvailableException extends EvaluatorException{

	public NoRepresentationAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
