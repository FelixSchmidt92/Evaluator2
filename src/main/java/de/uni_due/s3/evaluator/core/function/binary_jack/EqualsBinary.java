package de.uni_due.s3.evaluator.core.function.binary_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;

/**
 * This function checks if the 2 given Binary Strings are equal to each other It
 * is insensitive to leading Zeros. The String can also contain a leading '-'.
 * 
 * @author dlux
 *
 */
public class EqualsBinary extends Function {

	@Override
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentTypeException, FunctionInvalidArgumentException {
		try {
			String left = OMUtils.convertOMSTRToString(arguments.get(0));
			String right = OMUtils.convertOMSTRToString(arguments.get(1));
			// replace leading 0's
			left = left.replaceFirst("^0+(?!$)", "");
			right = right.replaceFirst("^0+(?!$)", "");

			// replace leading -0's with -
			left = left.replaceFirst("^-0+(?!$)", "-");
			right = right.replaceFirst("^-0+(?!$)", "-");

			long l = Long.decode(left);
			long r = Long.decode(right);

			if (l == r) {
				return OMSymbol.LOGIC1_TRUE;
			}

		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)String");
		} catch (NumberFormatException e) {
			throw new FunctionInvalidArgumentException(this,
					"One of the Binary Strings is either to long or contains invalid signs.");
		}

		return OMSymbol.LOGIC1_FALSE;
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
