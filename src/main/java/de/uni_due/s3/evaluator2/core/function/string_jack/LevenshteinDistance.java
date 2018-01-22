package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * 
 * @author striewe
 *
 */
public class LevenshteinDistance extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string1 = getStringSyntax(arguments.get(0));
		String string2 = getStringSyntax(arguments.get(1));
		
		return OMCreator.createOMI(new org.apache.commons.text.similarity.LevenshteinDistance().apply(string1, string2));
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}
}
