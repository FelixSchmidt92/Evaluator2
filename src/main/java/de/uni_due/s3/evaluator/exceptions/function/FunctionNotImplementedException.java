package de.uni_due.s3.evaluator.exceptions.function;

import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
/**
 * Is thrown when a requested function is not implemented
 * @author frichtscheid
 *
 */
public class FunctionNotImplementedException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8997318712583267590L;

	public FunctionNotImplementedException(OMS omsymbol){
		super("the requested function: " + omsymbol.getName() + "(cd:"+omsymbol.getCd()+") does not exist");
	}
	
	public FunctionNotImplementedException(String name){
		super("the requested function: " + name + " does not exist");
	}

}
