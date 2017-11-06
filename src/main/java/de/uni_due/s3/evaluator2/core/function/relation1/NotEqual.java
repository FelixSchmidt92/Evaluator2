package de.uni_due.s3.evaluator2.core.function.relation1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;

/**
 * Implements openmath relation neq. Example 10 != 5 => true
 */
public class NotEqual extends BinaryFunction {

	/**
	 * Tests if two given objects are not equal by using the object-equal method.
	 * Expects 2 arguments of any type
	 * 
	 * @throws EvaluatorException
	 * 
	 * @throws FunctionInvalidArgumentTypeException
	 * 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		try {
			double first = getDoubleSyntax(arguments.get(0));
			double second = getDoubleSyntax(arguments.get(1));
			return (first != second) ? OMSymbol.LOGIC1_TRUE : OMSymbol.LOGIC1_FALSE;
		} catch (FunctionInvalidArgumentTypeException e) {
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

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		return getLatexSyntax(arguments.get(0)) + "\\neq" + getLatexSyntax(arguments.get(1));
	}

}
