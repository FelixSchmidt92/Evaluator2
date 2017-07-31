package de.uni_due.s3.evaluator.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the correctly rounded positive nth root of a double value. If there
 * is only one param it returns sqrt.
 * 
 * @author spobel
 *
 */
public class Root extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException, OpenMathException {
		try {
			if (arguments.size() == 1) {
				Double argValue = OMUtils.convertOMToDouble(arguments.get(0));
				return OMUtils.convertDoubleToOMIOMF(Math.sqrt(argValue));
			} else {
				Double argValue = OMUtils.convertOMToDouble(arguments.get(0));
				Double nth = OMUtils.convertOMToDouble(arguments.get(1));
				if (argValue < 0) {
					if (nth % 2 == 1) {
						Double result = -1 * Math.pow(Math.E, Math.log(Math.abs(argValue)) / nth);
						return OMUtils.convertDoubleToOMIOMF(result);
					}
				}
				return OMUtils.convertDoubleToOMIOMF(Math.pow(Math.E, Math.log(Math.abs(argValue)) / nth));
			}
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer/Double/Float [(1)Integer]");
		}
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

}
