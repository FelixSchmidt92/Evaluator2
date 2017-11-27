package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Replaces each substring of this string that matches the given regular
 * expression with the given replacement.
 * 
 * An invocation of this method of the form str.replaceAll(regex, repl) yields
 * exactly the same result as the expression
 * 
 * java.util.regex.Pattern.compile(regex).matcher(str).replaceAll(repl) Note
 * that backslashes (\) and dollar signs ($) in the replacement string may cause
 * the results to be different than if it were being treated as a literal
 * replacement string; see Matcher.replaceAll. Use
 * java.util.regex.Matcher.quoteReplacement to suppress the special meaning of
 * these characters, if desired.
 * 
 * @author spobel
 *
 */
public class Replace extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string = getStringSyntax(arguments.get(0));
		String toReplace = getStringSyntax(arguments.get(1));
		String replaceWith = getStringSyntax(arguments.get(2));
		try {
			if (toReplace.length() == 1 && replaceWith.length() == 1) {
				return OMCreator.createOMSTR(string.replace(toReplace.charAt(0), replaceWith.charAt(0)));
			} else {
				return OMCreator.createOMSTR(string.replaceAll(toReplace, replaceWith));
			}
		} catch (IndexOutOfBoundsException e) {
			throw new FunctionInvalidArgumentException(this,
					"Second and Third argument has to be in range of (String) first argument.");
		}
	}

	@Override
	protected int minArgs() {
		return 3;
	}

	@Override
	protected int maxArgs() {
		return 3;
	}

}
