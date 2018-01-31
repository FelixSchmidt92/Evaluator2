package de.uni_due.s3.evaluator2.function.arith1;

import java.math.BigInteger;
import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements greatest-common-divisor with integers. For example gcd(12,6) = 3
 * 
 * @author frichtscheid
 *
 */
public class GCD extends Function {

	/**
	 * It expects two arguments of type OMI and returns an OMI
	 * 
	 * @return OMI
	 * @throws EvaluatorException
	 */
	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		BigInteger leftValue = BigInteger.valueOf(getIntegerSyntax(arguments.get(0)));
		BigInteger rightValue = BigInteger.valueOf(getIntegerSyntax(arguments.get(1)));
		return OMCreator.createOMIOMF(leftValue.gcd(rightValue).doubleValue());
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
		return "gcd(" + getSageSyntax(arguments.get(0)) + "," + getSageSyntax(arguments.get(1)) + ")";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {

		return "\\mbox{gcd}\\left(" + getLatexSyntax(arguments.get(0)) + "," + getLatexSyntax(arguments.get(1))
				+ "\\right)";
	}

}
