package de.uni_due.s3.evaluator.core.function.functions.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;

/**
 * Returns the smaller of two double values.
 * 
 * @author spobel
 *
 */
public class Min extends Function {

	/**
	 * Returns the smaller of two double values.
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try {
			Double leftValue = NumberUtils.convertOMIOMFToDouble(arguments.get(0));
			Double rightValue = NumberUtils.convertOMIOMFToDouble(arguments.get(1));
			Double result = Math.min(leftValue, rightValue);
			return NumberUtils.convertDoubleToOMIOMF(result);
		} catch (InputMismatchException np) {
			throw new FunctionInvalidArgumentTypeException(this, "Integer/Double/Float");
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
}
