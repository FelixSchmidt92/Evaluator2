package de.uni_due.s3.evaluator.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Implements charAt operation for strings.
 * 
 * Returns the char value at the specified index. An index ranges from 0 to
 * length() - 1. The first char value of the sequence is at index 0, the next at
 * index 1, and so on, as for array indexing.
 * 
 * If the char value specified by the index is a surrogate, the surrogate value
 * is returned.
 * 
 * @author spobel
 *
 */
public class CharAt extends Function {

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionException {
		try {
			String string = OMUtils.convertOMToString(arguments.get(0));
			int pos = OMUtils.convertOMToInteger(arguments.get(1));
			if (string.length() <= pos || pos < 0) {
				throw new FunctionInvalidArgumentException(this,
						"Second Argument of charAt is invalid. Not in Range of String.");
			}
			return OMCreator.createOMSTR(String.valueOf(string.charAt(pos)));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)Integer");
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
