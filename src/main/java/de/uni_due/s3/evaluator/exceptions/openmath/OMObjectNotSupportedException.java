package de.uni_due.s3.evaluator.exceptions.openmath;

import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

/**
 * Should be thrown when a OMOBJ-object contains a child that can't be processed or has no child to processes.
 * 
 * @author frichtscheid
 *
 */
public class OMObjectNotSupportedException extends EvaluatorException{

	public OMObjectNotSupportedException(Object omElement) {

		super("The object: "+omElement +" has a not supported child-element");

	}


	private static final long serialVersionUID = 1L;

}
