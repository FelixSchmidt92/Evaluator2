package de.uni_due.s3.evaluator.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

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
	protected Object execute(List<Object> arguments) throws FunctionException {
		String result = "";
		try {
			for (Object arg : arguments) {
				result = result.concat(OMUtils.convertOMToString(arg));
			}
			return OMCreator.createOMSTR(result);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)String, [(n)String]");
		}
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
