package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements plus operation for numbers Example: 3.56 + 4 = 7.56
 * 
 * @author frichtscheid
 *
 */
public class Plus extends Function {

	/**
	 * @throws OpenMathException Expects two arguments of type OMI or
	 * OMF @throws NoRepresentationAvailableException @throws
	 * CasNotAvailableException @throws
	 * FunctionInvalidNumberOfArgumentsException @throws
	 * CasEvaluationException @throws
	 * FunctionInvalidArgumentTypeException @throws
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException, OpenMathException {
		// evaluate this method in sage
		try {
			Double leftValue = OMUtils.convertOMIOMFToDouble(arguments.get(0));
			Double rightValue = OMUtils.convertOMIOMFToDouble(arguments.get(1));
			return OMUtils.convertDoubleToOMIOMF(leftValue + rightValue);
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
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		return getSageSyntax(arguments.get(0)) + " + " + getSageSyntax(arguments.get(1));
	}

}
