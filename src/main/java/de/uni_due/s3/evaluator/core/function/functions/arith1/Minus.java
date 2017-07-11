package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements arithmetic minus operation. Example 3-5 = -2
 * 
 * @author frichtscheid
 *
 */
public class Minus extends Function {

	/**
	 * Expects two arguments of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws CasEvaluationException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws NoRepresentationAvailableException
	 * @throws CasNotAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws OpenMathException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try {
			Double leftValue = NumberUtils.convertOMIOMFToDouble(arguments.get(0));
			Double rightValue = NumberUtils.convertOMIOMFToDouble(arguments.get(1));
			return NumberUtils.convertDoubleToOMIOMF(leftValue - rightValue);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		return getSageSyntax(arguments.get(0)) + " - " + getSageSyntax(arguments.get(1));
	}

}
