package de.uni_due.s3.evaluator.core.function.eval_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.PolyUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Inserts values for variable x in a term and calculates the result
 * 
 * @author spobel
 *
 */
public class EvalPolynomial extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {

		try {
			String term = getSageSyntax(arguments.get(0));
			Double value1 = OMUtils.convertOMToDouble(arguments.get(1));

			String sageVar = PolyUtils.getSageSyntaxVariableRepresentation(term + "; x;");

			StringBuilder sb = new StringBuilder();
			sb.append(sageVar);
			sb.append("x =");
			sb.append(value1);
			sb.append(";(");
			sb.append(term);
			sb.append(")");

			return sb.toString();
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Term, (1)Integer/Double/Float");
		}
	}
}
