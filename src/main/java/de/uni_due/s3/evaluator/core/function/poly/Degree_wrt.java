package de.uni_due.s3.evaluator.core.function.poly;

import java.util.List;

import de.uni_due.s3.evaluator.core.PolyUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Implements the polynomial degree-function with two arguments as specified in
 * openmath poly-cd. Example: deg(a^3+b^2,a) = 3 See
 * <a href="www.openmath.org/cd/poly.xhtml#degree_wrt">openmath</a>
 * 
 * @author spobel
 *
 */
public class Degree_wrt extends Function {

	/**
	 * Expects two arguments of type string. The first one is the polynomial,
	 * the second one the dependend variable If the second argument will be an
	 * empty string, the degree function returns the highest exponent
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		return Sage.evaluateInCAS(getPartialSageSyntax(arguments));
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		String polynom = getSageSyntax(arguments.get(0));
		String var = getSageSyntax(arguments.get(1));

		String sageVar = PolyUtils.getSageSyntaxVariableRepresentation(polynom + var);

		StringBuilder sb = new StringBuilder();
		sb.append(sageVar);
		sb.append("f = ");
		sb.append(polynom);
		sb.append("; f.degree(");
		sb.append(var);
		sb.append(")");

		return sb.toString();
	}

}
