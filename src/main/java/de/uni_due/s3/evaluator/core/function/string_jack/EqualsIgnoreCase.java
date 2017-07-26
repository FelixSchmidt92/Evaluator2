package de.uni_due.s3.evaluator.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;

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

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentException, FunctionInvalidArgumentTypeException {
		try {
			String string1 = OMUtils.convertOMToString(arguments.get(0));
			String string2 = OMUtils.convertOMToString(arguments.get(1));

			if (string1.equalsIgnoreCase(string2)) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
			}
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)String");
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