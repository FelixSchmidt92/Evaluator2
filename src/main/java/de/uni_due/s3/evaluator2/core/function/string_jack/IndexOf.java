package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements indexOf operation for strings.
 * 
 * Returns the index within this string of the first occurrence of the specified
 * substring, starting at the specified index.
 * 
 * The returned index is the smallest value k for which:
 * 
 * k >= fromIndex && this.startsWith(str, k)
 * 
 * If no such value of k exists, then -1 is returned.
 * 
 * @author spobel
 *
 */
public class IndexOf extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
			String string = getStringSyntax(arguments.get(0));
			String muster = getStringSyntax(arguments.get(1));
			int pos = 0;
			if (arguments.size() == 3) {
				pos = getIntegerSyntax(arguments.get(2));
			}
			if (string.length() <= pos || pos < 0) {
				throw new FunctionInvalidArgumentException(this,
						"Third Argument of indexOf is invalid. Not in Range of first String");
			}
			return OMCreator.createOMI(string.indexOf(muster, pos));
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
