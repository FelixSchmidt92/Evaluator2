package de.uni_due.s3.evaluator.core.function.string_jack;

import java.util.List;
import java.util.regex.PatternSyntaxException;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
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
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		try {
			String in = OMUtils.convertOMSTRToString(arguments.get(0));
			String regex = OMUtils.convertOMSTRToString(arguments.get(1));

			if (in.matches(regex)) {// can throw PatternSyntaxException
				return OMSymbol.LOGIC1_TRUE;
			}
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)String, (1)String");
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
