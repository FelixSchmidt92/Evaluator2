package de.uni_due.s3.evaluator2.exceptions.function;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;

public class FunctionInvalidResultTypeException extends FunctionException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5039985494952072908L;

	public FunctionInvalidResultTypeException(Function function) {
		super("Wrong result type in Function: " + function.getClass().getSimpleName() + ". Result has to be a terminal: "
				+ OMSymbol.TERMINALS.toString() + " or a symbolic expression like 'a+1' : " + OMSymbol.SYMBOLIC_EXPRESSION.toString());
	}

}
