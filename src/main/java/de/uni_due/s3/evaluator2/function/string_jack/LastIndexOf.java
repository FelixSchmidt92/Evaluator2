package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the index within this string of the last occurrence of the specified
 * substring, searching backward starting at the specified index. The returned
 * index is the largest value k for which: k <= fromIndex &&
 * this.startsWith(str, k)
 * 
 * If no such value of k exists, then -1 is returned.
 * 
 * @author spobel
 *
 */
public class LastIndexOf extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string = getStringSyntax(arguments.get(0));
		String muster = getStringSyntax(arguments.get(1));
		int pos = string.length() - 1;
		if (arguments.size() == 3) {
			pos = getIntegerSyntax(arguments.get(2));
		}
		if (pos < 0) {
			throw new FunctionInvalidArgumentException(this,
					"Third Argument of lastIndexOf is invalid. Not in Range of first String");
		}
		return OMCreator.createOMI(string.lastIndexOf(muster, pos));
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
