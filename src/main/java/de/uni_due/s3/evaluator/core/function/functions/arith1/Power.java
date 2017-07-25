package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the Math.pow function for functions Example: 2^3 = 8 or Usage:
 * power(2,3) = 8
 * 
 * @author frichtscheid
 *
 */
public class Power extends Function {

	/**
	 * Expects two arguments either of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws OpenMathException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException, OpenMathException   {
		try {
			Double b = OMUtils.convertOMIOMFToDouble(arguments.get(0));
			Double e = OMUtils.convertOMIOMFToDouble(arguments.get(1));
			return OMUtils.convertDoubleToOMIOMF(Math.pow(b, e));
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
		return "power(" + getSageSyntax(arguments.get(0)) + "," + getSageSyntax(arguments.get(1)) + ")";
	}

}
