package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements equals operation for strings.
 * 
 * Compares this string to the specified object. The result is true if and only
 * if the argument is not null and is a String object that represents the same
 * sequence of characters as this object.
 * 
 * @author spobel
 *
 */
public class Equals extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string1 = getStringSyntax(arguments.get(0));
		String string2 = getStringSyntax(arguments.get(1));

		if (string1.equals(string2)) {
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
