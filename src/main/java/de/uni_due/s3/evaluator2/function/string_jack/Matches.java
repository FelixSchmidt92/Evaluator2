package de.uni_due.s3.evaluator2.function.string_jack;

import java.util.List;
import java.util.regex.PatternSyntaxException;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Class checks iff the first argument matches a Regex-Pattern from the
 * second argument. Returns true if it does, false otherwise.
 * 
 * @author dlux
 *
 */
public class Matches extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String in = getStringSyntax(arguments.get(0));
		String regex = getStringSyntax(arguments.get(1));
		try {
			if (in.matches(regex)) {// can throw PatternSyntaxException
				return OMSymbol.LOGIC1_TRUE;
			}
		} catch (PatternSyntaxException e) {
			throw new FunctionInvalidArgumentException(this, "Second Argument, is not in Regex-Syntax");
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
