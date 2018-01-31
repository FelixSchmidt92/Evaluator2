package de.uni_due.s3.evaluator2.function.set1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * 
 * @author spobel
 *
 */
public class Intersect extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		java.util.Set<Object> set = new HashSet<>(getListSyntax(arguments.get(0)));
		set.retainAll(getListSyntax(arguments.get(1)));
		List<Object> list = new ArrayList<>(set);
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

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getLatexSyntax(arguments.get(0)) + "\\cap" + getLatexSyntax(arguments.get(1));
	}
}
