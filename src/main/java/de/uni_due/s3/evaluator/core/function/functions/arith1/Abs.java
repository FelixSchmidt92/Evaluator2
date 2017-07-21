package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the abs-function.
 * Example: abs(-3) = 3
 * @author frichtscheid
 *
 */
public class Abs extends Function{

	/**
	 * Expects one argument of type OMI or OMF
	 * @return OMI or OMF
	 * @throws OpenMathException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws  FunctionInvalidArgumentTypeException, OpenMathException{
		try {
			Double argValue = NumberUtils.convertOMIOMFToDouble(arguments.get(0));
			return NumberUtils.convertDoubleToOMIOMF(Math.abs(argValue));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this,"integer, float, double");
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
	public String getPartialSageSyntax(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		return "abs("+getSageSyntax(arguments.get(0))+")";
	}

}
