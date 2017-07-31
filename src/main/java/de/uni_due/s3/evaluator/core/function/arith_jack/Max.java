package de.uni_due.s3.evaluator.core.function.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;

/**
 * Returns the greater of two double values.
 * 
 * @author spobel
 *
 */
public class Max extends Function {

	/**
	 * Returns the greater of two double values.
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try {
			Double leftValue = OMUtils.convertOMToDouble(arguments.get(0));
			Double rightValue = OMUtils.convertOMToDouble(arguments.get(1));
			Double result = Math.max(leftValue, rightValue);
			return OMUtils.convertDoubleToOMIOMF(result);
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
