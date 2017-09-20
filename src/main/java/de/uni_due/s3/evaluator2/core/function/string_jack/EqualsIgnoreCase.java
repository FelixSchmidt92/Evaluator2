package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;

/**
 * Implements equalsIgnoreCase operation for strings.
 * 
 * Compares this String to another String, ignoring case considerations. Two
 * strings are considered equal ignoring case if they are of the same length and
 * corresponding characters in the two strings are equal ignoring case.
 * 
 * Two characters c1 and c2 are considered the same ignoring case if at least
 * one of the following is true:
 * 
 * The two characters are the same (as compared by the == operator) Applying the
 * method java.lang.Character.toUpperCase(char) to each character produces the
 * same result Applying the method java.lang.Character.toLowerCase(char) to each
 * character produces the same result
 * 
 * @author spobel
 *
 */
public class EqualsIgnoreCase extends Function {

	/**
	 * Expects two arguments of type String. If the arguments have a different type
	 * they will be converted to strings if possible. If not an error will be thrown
	 * @throws EvaluatorException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		String string1 = getStringSyntax(arguments.get(0));
		String string2 = getStringSyntax(arguments.get(1));

		if (string1.equalsIgnoreCase(string2)) {
			return OMSymbol.LOGIC1_TRUE;
		} else {
			return OMSymbol.LOGIC1_FALSE;
		}
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
