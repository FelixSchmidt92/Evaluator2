package de.uni_due.s3.evaluator2.core.function.rounding1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Returns the largest (closest to positive infinity) double value that is less
 * than or equal to the argument and is equal to a mathematical integer.
 * 
 * @author spobel
 *
 */
public class Floor extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
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
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		
		return "\\left\\lfloor " +getLatexSyntax(arguments.get(0))+ " \\right\\rfloor";
	}

}
