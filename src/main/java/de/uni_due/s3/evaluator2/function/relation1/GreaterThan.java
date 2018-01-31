package de.uni_due.s3.evaluator2.function.relation1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openmath relation1 gt. Example: 4 > 4 => true;
 */
public class GreaterThan extends BinaryFunction {

	/**
	 * Tests if the first argument is greater than the second argument. Expects 2
	 * arguments of type OMI or OMF
	 * 
	 * @throws EvaluatorException
	 * 
	 * @throws FunctionInvalidArgumentTypeException
	 * 
	 *             return true or false as OMS
	 */
	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		double first = getDoubleSyntax(arguments.get(0));
		double second = getDoubleSyntax(arguments.get(1));
		return (first > second) ? OMSymbol.LOGIC1_TRUE : OMSymbol.LOGIC1_FALSE;
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getSageSyntax(arguments.get(0)) + " > " + getSageSyntax(arguments.get(1));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getLatexSyntax(arguments.get(0)) + ">" + getLatexSyntax(arguments.get(1));
	}

}
