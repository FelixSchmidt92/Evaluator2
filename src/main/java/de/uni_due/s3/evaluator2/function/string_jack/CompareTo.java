package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements compareTo operation for strings.
 * 
 * Compares two strings lexicographically. The comparison is based on the
 * Unicode value of each character in the strings. The character sequence
 * represented by this String object is compared lexicographically to the
 * character sequence represented by the argument string. The result is a
 * negative integer if this String object lexicographically precedes the
 * argument string. The result is a positive integer if this String object
 * lexicographically follows the argument string. The result is zero if the
 * strings are equal; compareTo returns 0 exactly when the equals(Object) method
 * would return true.
 * 
 * This is the definition of lexicographic ordering. If two strings are
 * different, then either they have different characters at some index that is a
 * valid index for both strings, or their lengths are different, or both. If
 * they have different characters at one or more index positions, let k be the
 * smallest such index; then the string whose character at position k has the
 * smaller value, as determined by using the < operator, lexicographically
 * precedes the other string. In this case, compareTo returns the difference of
 * the two character values at position k in the two string -- that is, the
 * value:
 * 
 * this.charAt(k)-anotherString.charAt(k)
 * 
 * If there is no index position at which they differ, then the shorter string
 * lexicographically precedes the longer string. In this case, compareTo returns
 * the difference of the lengths of the strings -- that is, the value:
 * this.length()-anotherString.length()
 * 
 * @author spobel
 *
 */
public class CompareTo extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string1 = getStringSyntax(arguments.get(0));
		String string2 = getStringSyntax(arguments.get(1));
		return OMCreator.createOMI(string1.compareTo(string2));
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
