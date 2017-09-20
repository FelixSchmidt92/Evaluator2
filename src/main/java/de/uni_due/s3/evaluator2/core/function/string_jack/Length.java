package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Returns the length of this string. The length is equal to the number of
 * Unicode code units in the string.
 * 
 * @author spobel
 *
 */
public class Length extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
			String string =getStringSyntax(arguments.get(0));
			return OMCreator.createOMI(string.length());
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}
}
