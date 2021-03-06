package de.uni_due.s3.evaluator2.function.rounding1;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the largest (closest to positive infinity) double value that is less
 * than or equal to the argument and is equal to a mathematical integer.
 * 
 * @author spobel
 *
 */
public class Floor extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
			double value = getDoubleSyntax(arguments.get(0));
			value = Math.floor(value);
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
		
		return "\\left\\lfloor " +getLatexSyntax(arguments.get(0))+ " \\right\\rfloor";
	}

}
