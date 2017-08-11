package de.uni_due.s3.evaluator2.exceptions.cas;

/**
 * Is thrown when there is an error in the evaluation of a specific cas-command
 * @author spobel
 *
 */
public class CasEvaluationException extends CasException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7735807712213934788L;

	public CasEvaluationException(String message) {
		super(message);
	}
}
