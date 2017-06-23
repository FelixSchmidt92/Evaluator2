package de.uni_due.s3.evaluator.exceptions;

import de.uni_due.s3.openmath.OMOBJ;

/**
 * Should be thrown when a OMOBJ-object contains a child that can't be processed or has no child to processes.
 * 
 * @author frichtscheid
 *
 */
public class OMObjectNotSupportedException extends EvaluatorException{

	public OMObjectNotSupportedException(OMOBJ obj) {

		super("Object: "+obj.toString() +" has a not supported child-element");

	}


	private static final long serialVersionUID = 1L;

}
