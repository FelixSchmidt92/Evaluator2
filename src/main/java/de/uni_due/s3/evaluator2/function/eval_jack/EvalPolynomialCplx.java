package de.uni_due.s3.evaluator2.function.eval_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Inserts (2)replacement for (1)target in a (0)term and calculates the result
 * 
 * @author spobel
 *
 */
public class EvalPolynomialCplx extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String term = getSageSyntax(arguments.get(0));
		String sageVar = Sage.getSagePreVariable(term + "; x;");
		StringBuilder sb = new StringBuilder();
		sb.append(sageVar);
		sb.append("(");
		sb.append(term);
		sb.append(").expand()");

		String evaledTerm = getSageSyntax(Sage.evaluateInCAS(sb.toString()));
		String target = getSageSyntax(arguments.get(1));
		String replacement = getSageSyntax(arguments.get(2));
		String resultTerm = evaledTerm.replace(target, replacement);

		Object result = Sage.evaluateInCAS("(" + resultTerm + ")");
		return result;
	}

	@Override
	protected int minArgs() {
		return 3;
	}

	@Override
	protected int maxArgs() {
		return 3;
	}
}
