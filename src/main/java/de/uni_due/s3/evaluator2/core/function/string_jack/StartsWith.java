package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements endsWith operation for strings.
 * 
 * Tests if this string ends with the specified suffix.
 * 
 * @author spobel
 *
 */
public class StartsWith extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
			String string1 = getStringSyntax(arguments.get(0));
			String string2 = getStringSyntax(arguments.get(1));

			int pos = 0;

			if (arguments.size() == 3) {
				pos = getIntegerSyntax(arguments.get(2));
			}

			if (pos > string1.length()) {
				throw new FunctionInvalidArgumentException(this,
						"Third argument (Integer) has to be in range of first argument (String).");
			}

			if (string1.startsWith(string2, pos)) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
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
