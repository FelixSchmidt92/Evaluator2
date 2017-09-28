package de.uni_due.s3.evaluator2.core.function.nums1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openmath true
 * 
 * @author spobel
 *
 */
public class Rational extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, arguments);
	}

	@Override
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException {
		return true;
	}

	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException {
		Double arg1 = getDoubleSyntax(arguments.get(0));
		Double arg2 = getDoubleSyntax(arguments.get(1));

		return arg1 / arg2;
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		return "\\frac{" + arguments.get(0) + "}{" + arguments.get(1) + "}";
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return getSageSyntax(arguments.get(0)) + "/" + getSageSyntax(arguments.get(1));
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

}
