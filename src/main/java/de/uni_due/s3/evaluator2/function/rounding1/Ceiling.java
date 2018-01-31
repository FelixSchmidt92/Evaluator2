package de.uni_due.s3.evaluator2.function.rounding1;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the smallest (closest to negative infinity) double value that is
 * greater than or equal to the argument and is equal to a mathematical integer.
 * 
 * @author spobel
 *
 */
public class Ceiling extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		double value = getDoubleSyntax(arguments.get(0));
		value = Math.ceil(value);
		return OMCreator.createOMIOMF(value);
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
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "\\left\\lceil " + getLatexSyntax(arguments.get(0)) + " \\right\\rceil";
	}

}
