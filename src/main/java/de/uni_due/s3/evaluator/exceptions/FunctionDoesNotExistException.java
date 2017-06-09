package de.uni_due.s3.evaluator.exceptions;

public class FunctionDoesNotExistException extends FunctionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8997318712583267590L;

	public FunctionDoesNotExistException(String name){
		super("the requested function: "+name+ "does not exist");
	}

}
