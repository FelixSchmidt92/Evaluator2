package de.uni_due.s3.evaluator2.core.function.eval_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Inserts values for two different variables in a term and calculates the
 * result (as stated in
 * <a href="https://jack-community.org/wiki/index.php/EvalTermIn2Variables">
 * EvalTermIn2Variables</a>.
 * 
 * @author spobel
 *
 */
public class EvalTerm2 extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
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

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {

		String term = getSageSyntax(arguments.get(0));
		String value1 = getSageSyntax(arguments.get(1));
		String value2 = getSageSyntax(arguments.get(2));

		if ((value1.length() == 2 && value1.startsWith("'")) || (value2.length() == 2 && value2.startsWith("'"))) {
			// Case value1 or value 2 is only --> ''
			throw new FunctionInvalidArgumentException(this, "Arguments for this Function cannot be empty!");
		}

		String sageVar = Sage.getSagePreVariable(term + " " + value1 + " " + value2 + "; a; b; x; y;");

		StringBuilder sb = new StringBuilder();
		sb.append(sageVar);
		sb.append("x = a =");
		sb.append(value1);
		sb.append(";y = b =");
		sb.append(value2);
		sb.append(";(");
		sb.append(term);
		sb.append(")");

		return sb.toString();
	}
}
