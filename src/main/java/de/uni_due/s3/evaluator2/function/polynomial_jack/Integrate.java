package de.uni_due.s3.evaluator2.function.polynomial_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
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
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (getSageSyntax(arguments.get(1)).length() != 1) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Term, (1)Char");
		}
		String sage = getPartialSageSyntax(arguments);
		String sageVar = Sage.getSagePreVariable(sage); 
		Object result = Sage.evaluateInCAS(sageVar + sage);
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String term = getSageSyntax(arguments.get(0));
		String var = getSageSyntax(arguments.get(1));
		StringBuilder sb = new StringBuilder();
		sb.append("integral(");
		sb.append(term);
		sb.append(", ");
		sb.append(var);
		sb.append(")");

		return sb.toString();
	}
}
