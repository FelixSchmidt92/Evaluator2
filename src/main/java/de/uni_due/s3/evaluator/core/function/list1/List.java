package de.uni_due.s3.evaluator.core.function.list1;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implementation of List. This is a Terminal so it returns itself!
 * 
 * @author dlux
 *
 */
public class List extends Function {

	@Override
	protected Object execute(java.util.List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, arguments);
	}

	@Override
	protected int minArgs() {
		return 0;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialSageSyntax(java.util.List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		String set = "[";
		for (Object arg : arguments) {
			set += getSageSyntax(arg) + ", ";
		}
		set = set.substring(0, set.length() - 2); // Removing ", "

		return set + "]";
	}

}
