package de.uni_due.s3.evaluator2.core.function.transc2;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements ArcTan2 function. Example atan2(1,1) = 0.785398163397448
 * 
 * @author spobel
 *
 */
public class ArcTan2 extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (!OMTypeChecker.isOMNumber(arguments.get(0))) {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
		}
		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));

		if (OMTypeChecker.isOMS(result) && result.equals(OMSymbol.NUMS1_NAN)) {
			throw new FunctionInvalidArgumentException(this, "ArcTan2: both values cannot be zero at the same time.");
		}

		return result;
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
		return "arctan2(" + getSageSyntax(arguments.get(0)) + "," + getSageSyntax(arguments.get(1)) + ")";
	}

}
