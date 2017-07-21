package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openMath unary_minus for numbers Example: -3, -4.56
 * 
 * @author frichtscheid
 *
 */
public class UnaryMinus extends Function {

	/**
	 * Expects one argument of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws OpenMathException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException, OpenMathException {
		try {
			Double value = NumberUtils.convertOMIOMFToDouble(arguments.get(0));
			return NumberUtils.convertDoubleToOMIOMF(value * -1);
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

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		return "-" + getSageSyntax(arguments.get(0));
	}

}
