package de.uni_due.s3.evaluator.core.function.functions.transc1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

/**
 * Returns Euler's number e raised to the power of the argument. Example e^2,
 * e^10.432
 * 
 * 
 * @author spobel
 *
 */
public class Exp extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		Double result = null;
		try {
			result = Math.exp(NumberUtils.convertOMIOMFToDouble(arguments.get(0)));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
		}

		return NumberUtils.convertDoubleToOMIOMF(result);
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		return "exp(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
