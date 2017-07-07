package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMTypeChecker;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class CharAt extends Function {

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentException, FunctionInvalidArgumentTypeException {
		if (OMTypeChecker.isOMSTR(arguments.get(0)) && OMTypeChecker.isOMI(arguments.get(1))) {

			String string = ((OMSTR) arguments.get(0)).getContent();
			int pos = Integer.parseInt(((OMI) arguments.get(1)).getValue());

			if (string.length() <= pos || pos < 0) {
				throw new FunctionInvalidArgumentException(this,
						"Second Argument of charAt is invalid. Not in Range of String");
			}

			char chr = string.charAt(pos);

			return OMCreator.createOMSTR(String.valueOf(chr));

		} else {
			throw new FunctionInvalidArgumentTypeException(this, "String, Integer");
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
