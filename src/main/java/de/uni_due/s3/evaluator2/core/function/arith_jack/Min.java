package de.uni_due.s3.evaluator2.core.function.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the smaller of two double values.
 * 
 * @author spobel
 *
 */
public class Min extends Function {

	/**
	 * Returns the smaller of two double values.
	 * 
	 * @throws EvaluatorException
	 * @throws OpenMathException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Double leftValue = getDoubleSyntax(arguments.get(0));
		Double rightValue = getDoubleSyntax(arguments.get(1));
		Double result = Math.min(leftValue, rightValue);
		return OMCreator.createOMIOMF(result);
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
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "\\mbox{min}\\left(" + getLatexSyntax(arguments.get(0)) + "," + getLatexSyntax(arguments.get(1))
				+ "\\right)";
	}
}
