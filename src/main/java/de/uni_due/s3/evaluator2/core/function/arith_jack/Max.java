package de.uni_due.s3.evaluator2.core.function.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the greater of two double values.
 * 
 * @author spobel
 *
 */
public class Max extends Function {

	/**
	 * Returns the greater of two double values.
	 * 
	 * @throws EvaluatorException
	 * @throws OpenMathException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {

		Double leftValue = getDoubleSyntax(arguments.get(0));
		Double rightValue = getDoubleSyntax(arguments.get(1));
		Double result = Math.max(leftValue, rightValue);
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
		return "\\mbox{max}\\left(" + getLatexSyntax(arguments.get(0)) + "," + getLatexSyntax(arguments.get(1))
				+ "\\right)";
	}
}
