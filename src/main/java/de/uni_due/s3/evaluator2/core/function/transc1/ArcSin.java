package de.uni_due.s3.evaluator2.core.function.transc1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements ArcSin function. Example asin(1) = 1/2*pi = 1.57079632679490,
 * asin(0) = 0
 * 
 * @author spobel
 *
 */
public class ArcSin extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Object result;
		if (OMTypeChecker.isOMNumber(arguments.get(0))) {
			result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		} else {
			if (OMTypeChecker.isOMV(arguments.get(0))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SYMBOLIC_EXPRESSION)) {
				return OMCreator.createOMA(OMSymbol.TRANSC1_ARCSIN, arguments);
			}
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double, variable");
		}
		return result;
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
		return "arcsin(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
