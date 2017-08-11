package de.uni_due.s3.evaluator2.exceptions.representation;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;

/**
 * This class is the parent of other exceptions which will be thrown when there is no specific OpenMath representation for the specified system implemented
 * @author frichtscheid
 *
 */
public class NoRepresentationAvailableException extends EvaluatorException{

	public NoRepresentationAvailableException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
