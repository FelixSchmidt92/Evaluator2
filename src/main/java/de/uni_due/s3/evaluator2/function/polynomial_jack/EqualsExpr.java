package de.uni_due.s3.evaluator2.function.polynomial_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.function.Function;
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
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		try{
			String sage = getPartialSageSyntax(arguments);
			String sageVar = Sage.getSagePreVariable(sage);
		Object result = Sage.evaluateInCAS(sageVar + sage);
		return result;
		
		} catch(CasException ce) {
			throw new FunctionException("Error evaluating Arguments in " + this.getClass().getSimpleName(), ce);
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String term1 = getSageSyntax(arguments.get(0));
		String term2 = getSageSyntax(arguments.get(1));
		StringBuilder sb = new StringBuilder();
		sb.append("bool(");
		sb.append(term1);
		sb.append("==");
		sb.append(term2);
		sb.append(")");

		return sb.toString();
	}

}
