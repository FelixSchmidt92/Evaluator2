package de.uni_due.s3.evaluator.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Converts all of the characters in this String to lower case using the rules
 * of the default locale. This is equivalent to calling
 * toLowerCase(Locale.getDefault()).
 * 
 * @author spobel
 *
 */
public class ToLowerCase extends Function {

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentException, FunctionInvalidArgumentTypeException {
		try {
			String string = OMUtils.convertOMToString(arguments.get(0));
			return OMCreator.createOMSTR(string.toLowerCase());
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String");
		}
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