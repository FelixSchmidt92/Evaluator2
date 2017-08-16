package de.uni_due.s3.evaluator2.core.function.relation1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;

/**
 * Implements openmath relation neq. Example 10 != 5 => true
 */
public class NotEqual extends Function {

	/**
	 * Tests if two given objects are not equal by using the object-equal method.
	 * Expects 2 arguments of any type
	 * 
	 * @throws FunctionInvalidArgumentTypeException
	 * 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		try {
			double first = OMUtils.convertOMToDouble(arguments.get(0));
			double second = OMUtils.convertOMToDouble(arguments.get(1));
			return (first != second) ? OMSymbol.LOGIC1_TRUE : OMSymbol.LOGIC1_FALSE;
		} catch (InputMismatchException np) {
			if (arguments.get(0).equals(arguments.get(1)))
				return OMSymbol.LOGIC1_FALSE;
			else
				return OMSymbol.LOGIC1_TRUE;
		}
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return getSageSyntax(arguments.get(0)) + " != " + getSageSyntax(arguments.get(1));
	}

}
