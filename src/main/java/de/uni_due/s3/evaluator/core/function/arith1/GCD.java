package de.uni_due.s3.evaluator.core.function.arith1;

import java.math.BigInteger;
import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

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
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try {
			BigInteger leftValue = BigInteger.valueOf(OMUtils.convertOMIToInteger(arguments.get(0)));
			BigInteger rightValue = BigInteger.valueOf(OMUtils.convertOMIToInteger(arguments.get(1)));
			return OMUtils.convertDoubleToOMIOMF(leftValue.gcd(rightValue).doubleValue());
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "integer");
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		return "gcd(" + getSageSyntax(arguments.get(0)) + "," + getSageSyntax(arguments.get(1)) + ")";
	}

}
