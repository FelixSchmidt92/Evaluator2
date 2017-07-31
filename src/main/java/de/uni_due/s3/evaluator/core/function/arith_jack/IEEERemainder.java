package de.uni_due.s3.evaluator.core.function.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Computes the remainder operation on two arguments as prescribed by the IEEE
 * 754 standard. The remainder value is mathematically equal to f1 - f2 × n,
 * where n is the mathematical integer closest to the exact mathematical value
 * of the quotient f1/f2, and if two mathematical integers are equally close to
 * f1/f2, then n is the integer that is even. If the remainder is zero, its sign
 * is the same as the sign of the first argument.
 * 
 * @author spobel
 *
 */
public class IEEERemainder extends Function {

	/**
	 * Expects two argument of type OMI
	 * 
	 * @return OMI
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws OpenMathException
	 * @throws FunctionInvalidArgumentException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException, OpenMathException, FunctionInvalidArgumentException {
		try {
			Double leftValue = OMUtils.convertOMToDouble(arguments.get(0));
			Double rightValue = OMUtils.convertOMToDouble(arguments.get(1));
			if (rightValue == 0.0) {
				throw new FunctionInvalidArgumentException(this, "Second argument for IEEERemainder has to be unequal 0.0 .");
			}
			return OMUtils.convertDoubleToOMIOMF(Math.IEEEremainder(leftValue, rightValue));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(1)Integer/Double/Float, (2)Integer/Double/Float");
		}
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 2;
	}
}
