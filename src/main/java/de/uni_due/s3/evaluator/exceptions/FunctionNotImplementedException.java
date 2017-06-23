package de.uni_due.s3.evaluator.exceptions;

public class FunctionNotImplementedException extends FunctionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8997318712583267590L;

	public FunctionNotImplementedException(String name){
		super("the requested function: "+name+ "does not exist");
	}
	
	public FunctionNotImplementedException(String name, String cd){
		super("the requested function: "+name+ " with cd: "+cd+" does not exist");
	}

}
