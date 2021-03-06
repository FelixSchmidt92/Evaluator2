package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns a string whose value is this string, with any leading and trailing
 * whitespace removed.
 * 
 * If this String object represents an empty character sequence, or the first
 * and last characters of character sequence represented by this String object
 * both have codes greater than '\u005Cu0020' (the space character), then a
 * reference to this String object is returned.
 * 
 * Otherwise, if there is no character with a code greater than '\u005Cu0020' in
 * the string, then a String object representing an empty string is returned.
 * 
 * Otherwise, let k be the index of the first character in the string whose code
 * is greater than '\u005Cu0020', and let m be the index of the last character
 * in the string whose code is greater than '\u005Cu0020'. A String object is
 * returned, representing the substring of this string that begins with the
 * character at index k and ends with the character at index m-that is, the
 * result of this.substring(k, m + 1).
 * 
 * This method may be used to trim whitespace (as defined above) from the
 * beginning and end of a string.
 * 
 * @author spobel
 *
 */
public class Trim extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
			String string = getStringSyntax(arguments.get(0));
			return OMCreator.createOMSTR(string.trim());
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
