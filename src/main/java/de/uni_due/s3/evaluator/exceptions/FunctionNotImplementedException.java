package de.uni_due.s3.evaluator.exceptions;

import de.uni_due.s3.openmath.OMS;

public class FunctionNotImplementedException extends FunctionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8997318712583267590L;

	public FunctionNotImplementedException(OMS oms){
		super("the requested function: " + oms.getName() + " with cd: " + oms.getCd() + " does not exist");
	}
	
	public FunctionNotImplementedException(String name){
		super("the requested function: " + name + " does not exist");
	}

}
