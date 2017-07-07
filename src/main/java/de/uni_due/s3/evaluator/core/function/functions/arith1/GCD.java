package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.math.BigInteger;
import java.util.List;


import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMI;

/**
 * Implements greatest-common-divisor with integers.
 * For example gcd(2,3) = 6
 * @author frichtscheid
 *
 */
public class GCD extends Function {
	
	/**
	 *  It expects two arguments of type OMI and returns an OMI
	 *  @return OMI
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		Object left = arguments.get(0);
		Object right = arguments.get(1);
		if(left instanceof OMI && right instanceof OMI){
			BigInteger leftValue = BigInteger.valueOf(NumberUtils.valueOf((OMI) left));
			BigInteger rightValue = BigInteger.valueOf(NumberUtils.valueOf((OMI)right));
			return NumberUtils.convertDoubleToOMIOMF(leftValue.gcd(rightValue).doubleValue());
		}else {
			throw new FunctionInvalidArgumentTypeException(this,"integer");
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
	public String getPartialSageSyntax(List<Object> arguments) throws  FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		return "gcd("+getSageSyntax(arguments.get(0))+","+getSageSyntax(arguments.get(1));
	}

}
