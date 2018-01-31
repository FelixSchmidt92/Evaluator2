package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Converts all of the characters in this String to upper case using the rules
 * of the default locale. This method is equivalent to
 * toUpperCase(Locale.getDefault()).
 * 
 * @author spobel
 *
 */
public class ToUpperCase extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string = getStringSyntax(arguments.get(0));
		return OMCreator.createOMSTR(string.toUpperCase());
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
