package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Returns the length of this string. The length is equal to the number of
 * Unicode code units in the string.
 * 
 * @author spobel
 *
 */
public class Length extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		try {
			String string = OMUtils.convertOMToString(arguments.get(0));
			return OMCreator.createOMI(string.length());
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String");
		}
	}

	@Override
	protected boolean keepOriginalTextValue() {
		return true;
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}
}
