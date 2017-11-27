package de.uni_due.s3.evaluator2.core.function.set1;

import java.util.HashSet;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * 
 * @author spobel
 *
 */
public class Subset extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		java.util.Set<Object> set1 = new HashSet<>(getListSyntax(arguments.get(0)));
		java.util.Set<Object> set2 = new HashSet<>(getListSyntax(arguments.get(1)));
		if (set2.containsAll(set1)) {
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
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getLatexSyntax(arguments.get(0)) + "\\subset" + getLatexSyntax(arguments.get(1));
	}
}
