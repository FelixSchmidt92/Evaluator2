package de.uni_due.s3.evaluator.exceptions;

import de.uni_due.s3.evaluator.core.functionData.OMSymbol;

public class FunctionNotImplementedException extends FunctionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8997318712583267590L;

	public FunctionNotImplementedException(OMSymbol omsymbol){
		super("the requested function: " + omsymbol.getName() + " with cd: " + omsymbol.getCd() + " does not exist");
	}
	
	public FunctionNotImplementedException(String name){
		super("the requested function: " + name + " does not exist");
	}

}
