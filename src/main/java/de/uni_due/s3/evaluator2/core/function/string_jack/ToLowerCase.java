package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Converts all of the characters in this String to lower case using the rules
 * of the default locale. This is equivalent to calling
 * toLowerCase(Locale.getDefault()).
 * 
 * @author spobel
 *
 */
public class ToLowerCase extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string = getStringSyntax(arguments.get(0));
		return OMCreator.createOMSTR(string.toLowerCase());
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
