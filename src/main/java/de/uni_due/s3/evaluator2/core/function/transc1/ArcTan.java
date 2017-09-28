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
 * Implements ArcTan function. Example atan(1) = 1/4*pi = 0.785398163397448,
 * atan(0) = 0
 * 
 * @author spobel
 *
 */
public class ArcTan extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Object result;
		if (OMTypeChecker.isOMNumber(arguments.get(0))) {
			result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		} else {
			if (OMTypeChecker.isOMV(arguments.get(0))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SYMBOLIC_EXPRESSION)) {
				return OMCreator.createOMA(OMSymbol.TRANSC1_ARCTAN, arguments);
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "arctan(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
