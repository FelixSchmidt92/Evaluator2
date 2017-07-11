package de.uni_due.s3.evaluator.exceptions.function;

public class FunctionNotImplementedException extends FunctionException {

	/**
	 * This is a wrapper for FunctionNotImplementedRuntimeException thrown in parser.
	 */
	private static final long serialVersionUID = 5655763396362526145L;

	public FunctionNotImplementedException(FunctionNotImplementedRuntimeException e) {
		super(e.getMessage());
	}
}
