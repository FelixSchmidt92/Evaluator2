package de.uni_due.s3.evaluator.exceptions;

public class FunctionContentDictionaryMismatch extends FunctionException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2904874347461184609L;

	public FunctionContentDictionaryMismatch(String functionName, String functionCD, String requestedCD) {
		super("Function "+functionName+" has the wrong content dictionary ("+requestedCD+" instead of "+functionCD);
	}

}
