package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements concat operation for strings.
 * 
 * Concatenates the specified string to the end of this string.
 * 
 * If the length of the argument string is 0, then this String object is
 * returned. Otherwise, a String object is returned that represents a character
 * sequence that is the concatenation of the character sequence represented by
 * this String object and the character sequence represented by the argument
 * string.
 * 
 * Examples:
 * 
 * "cares".concat("s") returns "caress" "to".concat("get").concat("her") returns
 * "together"
 * 
 * @author spobel
 *
 */
public class Concat extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String result = "";
		for (Object arg : arguments) {
			result = result.concat(getStringSyntax(arg));
		}
		return OMCreator.createOMSTR(result);
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

}
