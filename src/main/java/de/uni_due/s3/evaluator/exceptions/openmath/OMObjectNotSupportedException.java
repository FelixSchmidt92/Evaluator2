package de.uni_due.s3.evaluator.exceptions.openmath;


/**
 * Should be thrown when a OMOBJ-object contains a child that can't be processed or has no child to processes.
 * 
 * @author frichtscheid
 *
 */
public class OMObjectNotSupportedException extends OpenmathException{

	public OMObjectNotSupportedException(Object omElement) {

		super("The object: "+omElement +" has a not supported child-element");

	}


	private static final long serialVersionUID = 1L;

}
