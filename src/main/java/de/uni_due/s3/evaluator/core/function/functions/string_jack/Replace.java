package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;

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
	protected Object execute(List<Object> arguments)
			throws FunctionInvalidArgumentTypeException, FunctionInvalidArgumentException {
		try {
			String string = NumberUtils.convertOMSTRToString(arguments.get(0));
			String toReplace = NumberUtils.convertOMSTRToString(arguments.get(1));
			String replaceWith = NumberUtils.convertOMSTRToString(arguments.get(2));

			if (toReplace.length() == 1 && replaceWith.length() == 1) {
				return OMCreator.createOMSTR(string.replace(toReplace.charAt(0), replaceWith.charAt(0)));
			} else {
				return OMCreator.createOMSTR(string.replaceAll(toReplace, replaceWith));
			}
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)String/Char/Regex, (2)String/Char");
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
