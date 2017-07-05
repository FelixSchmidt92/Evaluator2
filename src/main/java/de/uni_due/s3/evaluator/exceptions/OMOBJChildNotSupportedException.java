package de.uni_due.s3.evaluator.exceptions;

import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;

public class OMOBJChildNotSupportedException extends EvaluatorException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 342462903096985914L;

	public OMOBJChildNotSupportedException(OMOBJ omobj) {
		super(omobj+" has a child that is not supported");
	}

}
