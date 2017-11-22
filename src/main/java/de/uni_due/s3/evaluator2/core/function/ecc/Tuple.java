package de.uni_due.s3.evaluator2.core.function.ecc;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the Tuple itself
 * 
 * A Tuple can be (1, 2) or ('str', x, 1, INFINITY)
 * 
 * It is like an sorted List. In Sage this Tuple can be called with 
 * '[var=a][n]' -> n the n-th element beginning at 0.
 * 
 * Can be constructed with tuple(inputs...)
 * 
 * @author dlux
 *
 */
public class Tuple extends ConstructorFunction {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.ECC_TUPLE, arguments);
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		ArrayList<Object> list = new ArrayList<>();
		list.add(OMCreator.createOMA(OMSymbol.ECC_TUPLE, omel));
		return list;
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		String latex = "(";
		for (Object obj : arguments) {
			latex += getLatexSyntax(obj) + ", ";
		}
		String res = latex.substring(0, latex.length() - 2);
		return res + ")";
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException {
		String r = "tuple(";
		for (Object obj : arguments) {
			r += getLatexSyntax(obj) + ", ";
		}
		String res = r.substring(0, r.length() - 2);
		return res + ", TRUE)";
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {

		String sage = "tuple([";
		for (Object obj : arguments) {
			sage += getLatexSyntax(obj) + ", ";
		}
		String res = sage.substring(0, sage.length() - 2) + "])";

		return res;
	}

}
