package de.uni_due.s3.evaluator2.function.list2;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implementation of List. This is a Terminal so it returns itself!
 * 
 * @author spobel
 *
 */
public class Append extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(java.util.List<Object> arguments) throws OpenMathException, EvaluatorException {
		List<Object> list = getListSyntax(arguments.get(0));
		list.addAll(getListSyntax(arguments.get(1)));
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, list);
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
