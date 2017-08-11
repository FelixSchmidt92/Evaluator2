package de.uni_due.s3.evaluator2.core.function.transc_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;

/**
 * Converts an angle measured in radians to an approximately equivalent angle
 * measured in degrees. The conversion from radians to degrees is generally
 * inexact; users should not expect cos(toRadians(90.0)) to exactly equal 0.0.
 * 
 * @author spobel
 *
 */
public class ToDegree extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		try {
			Double value = OMUtils.convertOMToDouble(arguments.get(0));
			return OMUtils.convertDoubleToOMIOMF(Math.toDegrees(value));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer");
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

}
