package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OpenMathException;

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
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string1 = getStringSyntax(arguments.get(0));
		String string2 = getStringSyntax(arguments.get(1));

		if (string1.endsWith(string2)) {
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
		return 2;
	}

}
