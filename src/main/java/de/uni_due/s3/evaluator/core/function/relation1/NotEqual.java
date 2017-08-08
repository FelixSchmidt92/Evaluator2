package de.uni_due.s3.evaluator.core.function.relation1;

import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

/**
 * Implements openmath relation neq. Example 10 != 5 => true
 */
public class NotEqual extends Function {

	/**
	 * Tests if two given objects are not equal by using the object-equal
	 * method. Expects 2 arguments of any type
	 * 
	 * @throws FunctionInvalidArgumentTypeException
	 * 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		try {
			boolean result = arguments.get(0).equals(arguments.get(1));
			return (result) ? OMSymbol.LOGIC1_FALSE : OMSymbol.LOGIC1_TRUE;
		} catch (NullPointerException np) {
			throw new FunctionInvalidArgumentTypeException(this, "everything except null");
		}
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		// TODO Auto-generated method stub
		return getSageSyntax(arguments.get(0)) + " != " + getSageSyntax(arguments.get(1));
	}

}
