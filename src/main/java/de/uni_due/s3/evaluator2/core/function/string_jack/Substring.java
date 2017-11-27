package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns a string that is a substring of this string. The substring begins at
 * the specified beginIndex and extends to the character at index endIndex - 1.
 * Thus the length of the substring is endIndex-beginIndex.
 * 
 * Examples:
 * 
 * "hamburger".substring(4, 8) returns "urge" "smiles".substring(1, 5) returns
 * "mile"
 * 
 * @author spobel
 *
 */
public class Substring extends Function {

	/**
	 * Expects two or three arguments. The first one has to be a string and the
	 * second the index at which to start. The third argument is optional and
	 * represents the index at which to stop. If the last argument is not specified
	 * it will use the length of the first argument as index.
	 * @throws EvaluatorException 
	 * 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string = getStringSyntax(arguments.get(0));
		int begin = getIntegerSyntax(arguments.get(1));
		int end = string.length();
		if (arguments.size() == 3) {
			end = getIntegerSyntax(arguments.get(2));
		}
		try {
			return OMCreator.createOMSTR(string.substring(begin, end));
		} catch (IndexOutOfBoundsException e) {
			throw new FunctionInvalidArgumentException(this,
					"Second and Third argument has to be in range of (String) first argument.");
		}
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 3;
	}

}
