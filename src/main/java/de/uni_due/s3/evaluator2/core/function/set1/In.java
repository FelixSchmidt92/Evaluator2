package de.uni_due.s3.evaluator2.core.function.set1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;

/**
 * Implements the in Function for List.
 * 
 * @author spobel
 *
 */
public class In extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		List<Object> list = getListSyntax(arguments.get(1));
		if (list.contains(arguments.get(0)) || arguments.get(0).equals(OMSymbol.SET1_EMPTYSET)) {
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

	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		return getLatexSyntax(arguments.get(0)) + "\\in" + getLatexSyntax(arguments.get(1));
	}
}