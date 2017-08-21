package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.PolyUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Tests if two expressions are the same. Example: x^2 - 5*x + 6 == (x-2)(x-3)
 * 
 * @author spobel
 *
 */
public class EqualsExpr extends Function {

	/**
	 * Expects two polynomials of type OMA.
	 * 
	 * @return OMS(logic_true or logic_false)
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		return result;
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		String term1 = getSageSyntax(arguments.get(0));
		String term2 = getSageSyntax(arguments.get(1));

		String sageVar = PolyUtils.getSageSyntaxVariableRepresentation(term1 + term2);

		StringBuilder sb = new StringBuilder();

		sb.append(sageVar);
		sb.append("bool(");
		sb.append(term1);
		sb.append("==");
		sb.append(term2);
		sb.append(")");

		return sb.toString();
	}

}
