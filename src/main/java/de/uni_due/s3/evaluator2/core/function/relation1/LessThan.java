package de.uni_due.s3.evaluator2.core.function.relation1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;

/**
 * Implements openmath arithmetic relation lt. Example:
 * 
 * @author frichtscheid
 *
 */
public class LessThan extends BinaryFunction {

	/**
	 * Tests if the first argument is less than the second argument. Expects 2
	 * arguments of type OMI or OMF
	 * 
	 * @throws EvaluatorException
	 * 
	 * @throws FunctionInvalidArgumentTypeException
	 * 
	 *             return true or false as OMS
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		double first = getDoubleSyntax(arguments.get(0));
		double second = getDoubleSyntax(arguments.get(1));
		return (first < second) ? OMSymbol.LOGIC1_TRUE : OMSymbol.LOGIC1_FALSE;
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
		return getSageSyntax(arguments.get(0)) + " < " + getSageSyntax(arguments.get(1));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		return getLatexSyntax(arguments.get(0)) + "<" + getLatexSyntax(arguments.get(1));
	}

}
