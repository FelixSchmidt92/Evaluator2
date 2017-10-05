package de.uni_due.s3.evaluator2.core.function.eval_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Inserts values for variable x in a term and calculates the result
 * 
 * @author spobel
 *
 */
public class EvalPolynomial extends Function {

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
		Double value1 = getDoubleSyntax(arguments.get(1));

		String sageVar = Sage.getSagePreVariable(term + "; x;");

		String[] varArray = sageVar.substring(5, sageVar.length()-3).split(" ");
		
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(sageVar);
		for(String var : varArray) {
			sb.append(var + " =");
			sb.append(value1);
			sb.append(";");
		}

		sb.append("(");
		sb.append(term);
		sb.append(")");

		return sb.toString();
	}
}
