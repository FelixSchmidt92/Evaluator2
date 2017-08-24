package de.uni_due.s3.evaluator2.core.function.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;

/**
 * Returns the smaller of two double values.
 * 
 * @author spobel
 *
 */
public class Min extends Function {

	/**
	 * Returns the smaller of two double values.
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try {
			Double leftValue = OMUtils.convertOMToDouble(arguments.get(0));
			Double rightValue = OMUtils.convertOMToDouble(arguments.get(1));
			Double result = Math.min(leftValue, rightValue);
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
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		return "\\mbox{min}\\left(" + getLatexSyntax(arguments.get(0)) +","+getLatexSyntax(arguments.get(1))  + "\\right)";
	}
}
