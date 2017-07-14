package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class CharAt extends Function {

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentException, FunctionInvalidArgumentTypeException {
		try {
			String string = NumberUtils.convertOMSTRToSring(arguments.get(0));
			int pos = NumberUtils.convertOMIToInteger(arguments.get(1));
			if (string.length() <= pos || pos < 0) {
				throw new FunctionInvalidArgumentException(this,
						"Second Argument of charAt is invalid. Not in Range of String");
			}
			return OMCreator.createOMSTR(String.valueOf(string.charAt(pos)));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "[0]String, [1]Integer");
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
