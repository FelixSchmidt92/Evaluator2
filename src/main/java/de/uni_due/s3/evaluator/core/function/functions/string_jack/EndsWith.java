package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;

/**
 * Implements endsWith operation for strings.
 * 
 * Tests if this string ends with the specified suffix.
 * 
 * @author spobel
 *
 */
public class EndsWith extends Function {

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentException, FunctionInvalidArgumentTypeException {
		try {
			String string1 = NumberUtils.convertOMSTRToString(arguments.get(0));
			String string2 = NumberUtils.convertOMSTRToString(arguments.get(1));

			if (string1.endsWith(string2)) {
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
