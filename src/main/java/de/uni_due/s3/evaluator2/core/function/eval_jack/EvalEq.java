package de.uni_due.s3.evaluator2.core.function.eval_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.PolyUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Subtracts two given expression and return the result as stated in
 * <a href="https://jack-community.org/wiki/index.php/EvalEq">EvalEq</a> Example
 * : evalEq('x^2-1','x') => 'x^2-x-1
 * 
 * @author spobel
 *
 */
public class EvalEq extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		String term1 = getSageSyntax(arguments.get(0));
		String term2 = getSageSyntax(arguments.get(1));
		if ((term1.length() == 2 && term1.startsWith("'")) || (term2.length() == 2 && term2.startsWith("'"))) {
			// Case term1 or term2 is --> ''
			throw new FunctionInvalidArgumentException(this, "Input for this Function cannot be empty!");
		}

		String sageVar = PolyUtils.getSageSyntaxVariableRepresentation(term1 + term2);

		StringBuilder sb = new StringBuilder();
		sb.append(sageVar);
		sb.append("expand(("); // Expand: shorts Expression
		sb.append(term1);
		sb.append(") - (");
		sb.append(term2);
		sb.append("))");
		return sb.toString();
	}
}
