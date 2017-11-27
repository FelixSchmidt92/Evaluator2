package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the abs-function. Example: abs(-3) = 3
 * 
 * @author frichtscheid
 *
 */
public class Abs extends Function {

	/**
	 * Expects one argument of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws OpenMathException
	 * @throws EvaluatorException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		try {
			Double argValue = getDoubleSyntax(arguments.get(0));
			return OMCreator.createOMIOMF(Math.abs(argValue));
		} catch (FunctionInvalidArgumentTypeException e) {
			if (OMTypeChecker.isOMV(arguments.get(0))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SYMBOLIC_EXPRESSION)) {
				return OMCreator.createOMA(OMSymbol.ARITH1_ABS, arguments);
			}
			throw e;
		}
	}
	
	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "abs(" + getSageSyntax(arguments.get(0)) + ")";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {

		return "\\left|" + getLatexSyntax(arguments.get(0)) + "\\right|";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "|" + getStringSyntax(arguments.get(0)) + "|";
	}

}
