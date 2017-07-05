package de.uni_due.s3.evaluator.exceptions.openmath;

import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;

public class OMOBJChildNotSupportedException extends OpenmathException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 342462903096985914L;

	public OMOBJChildNotSupportedException(OMOBJ omobj) {
		super(omobj+" has a child that is not supported");
	}

}
