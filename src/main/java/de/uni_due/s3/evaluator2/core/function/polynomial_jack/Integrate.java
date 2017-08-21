package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.PolyUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Function to integrate a term. (3*x^2, x) -> x^3
 * 
 * @author spobel
 *
 */
public class Integrate extends Function {

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
		String term = getSageSyntax(arguments.get(0));
		String var = getSageSyntax(arguments.get(1));

		if (var.length() != 1) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Term, (1)Char");
		}

		String sageVar = PolyUtils.getSageSyntaxVariableRepresentation(term + var);

		StringBuilder sb = new StringBuilder();

		sb.append(sageVar);
		sb.append("integral(");
		sb.append(term);
		sb.append(", ");
		sb.append(var);
		sb.append(")");

		return sb.toString();
	}
}
