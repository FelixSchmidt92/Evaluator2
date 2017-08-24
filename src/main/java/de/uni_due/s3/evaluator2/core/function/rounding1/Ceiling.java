package de.uni_due.s3.evaluator2.core.function.rounding1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;

/**
 * Returns the smallest (closest to negative infinity) double value that is
 * greater than or equal to the argument and is equal to a mathematical integer.
 * 
 * @author spobel
 *
 */
public class Ceiling extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		try {
			double value = OMUtils.convertOMToDouble(arguments.get(0));
			value = Math.ceil(value);
			return OMUtils.convertDoubleToOMIOMF(value);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer/Double/Float");
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
	public String getPartialLatexSyntax(List<String> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		return "\\left\\lceil " +arguments.get(0)+ " \\right\\rceil";
	}

}
