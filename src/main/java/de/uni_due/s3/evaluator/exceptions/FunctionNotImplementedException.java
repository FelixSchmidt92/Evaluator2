package de.uni_due.s3.evaluator.exceptions;

import de.uni_due.s3.evaluator.omdictionary.OMSymbol;

public class FunctionNotImplementedException extends FunctionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8997318712583267590L;

	public FunctionNotImplementedException(OMSymbol omSymbol){
		super("the requested function: " + omSymbol.getName() + " with cd: " + omSymbol.getCd() + " does not exist");
	}
	
	public FunctionNotImplementedException(String name){
		super("the requested function: " + name + " does not exist");
	}

}
