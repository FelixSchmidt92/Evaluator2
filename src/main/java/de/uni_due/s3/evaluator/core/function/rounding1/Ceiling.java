package de.uni_due.s3.evaluator.core.function.rounding1;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;

/**
 * Returns the smallest (closest to negative infinity) double value that is
 * greater than or equal to the argument and is equal to a mathematical integer.
 * 
 * @author spobel
 *
 */
public class Ceiling extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try {
			double value = OMUtils.convertOMIOMFToDouble(arguments.get(0));
			value = Math.ceil(value);
			return OMUtils.convertDoubleToOMIOMF(value);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer/Double/Float");
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
