package de.uni_due.s3.evaluator2.core.function.arith1;

import java.math.BigInteger;
import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;

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
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		try {
			BigInteger leftValue = BigInteger.valueOf(getIntegerSyntax(arguments.get(0)));
			BigInteger rightValue = BigInteger.valueOf(getIntegerSyntax(arguments.get(1)));
			return OMUtils.convertDoubleToOMIOMF(leftValue.gcd(rightValue).doubleValue());
		} catch (NoRepresentationAvailableException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(1)Integer, (2)Integer");
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
		return "gcd(" + getSageSyntax(arguments.get(0)) + "," + getSageSyntax(arguments.get(1)) + ")";
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException {
	
		return "\\mbox{gcd}\\left("+getLatexSyntax(arguments.get(0)) +","+getLatexSyntax(arguments.get(1)) +"\\right)";
	}

}
