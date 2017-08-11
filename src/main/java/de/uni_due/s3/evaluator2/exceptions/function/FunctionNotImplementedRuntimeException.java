package de.uni_due.s3.evaluator2.exceptions.function;

import de.uni_due.s3.openmath.jaxb.OMS;

/**
 * Is thrown when a requested function is not implemented
 * 
 * @author frichtscheid
 *
 */
public class FunctionNotImplementedRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8997318712583267590L;

	public FunctionNotImplementedRuntimeException(OMS omsymbol) {
		super("the requested function: " + omsymbol.getName() + "(cd:" + omsymbol.getCd() + ") does not exist");
	}

	public FunctionNotImplementedRuntimeException(String name) {
		super("the requested function: " + name + " does not exist");
	}

}
