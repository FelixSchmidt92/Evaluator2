package de.uni_due.s3.evaluator2.core.function.transc1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Returns the natural logarithm (base e) of a double value.
 * 
 * 
 * @author spobel
 *
 */
public class Log extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		try {
			Double value = OMUtils.convertOMToDouble(arguments.get(0));
			if (value <= 0) {
				throw new FunctionInvalidArgumentException(this, "Function Log expects values greater than zero.");
			}
			Double result = Math.log(value);
			return OMCreator.createOMIOMF(result);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
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
