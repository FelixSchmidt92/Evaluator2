package de.uni_due.s3.evaluator.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

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
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentException, FunctionInvalidArgumentTypeException {
		try {
			String string = OMUtils.convertOMSTRToString(arguments.get(0));
			String muster = OMUtils.convertOMSTRToString(arguments.get(1));
			int pos = 0;
			if (arguments.size() == 3) {
				pos = OMUtils.convertOMIToInteger(arguments.get(2));
			}
			if (string.length() <= pos || pos < 0) {
				throw new FunctionInvalidArgumentException(this,
						"Third Argument of indexOf is invalid. Not in Range of first String");
			}
			return OMCreator.createOMI(string.indexOf(muster, pos));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)String, [(2)Integer]");
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
