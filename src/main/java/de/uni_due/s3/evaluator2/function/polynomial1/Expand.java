package de.uni_due.s3.evaluator2.function.polynomial1;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Function to expand a term. Example: (2+x)*(2-x) -> 4 - x^2
 * 
 * @author spobel
 *
 */
public class Expand extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {	
		String term = getSageSyntax(arguments.get(0));
		if (term.equals("''"))
			throw new FunctionInvalidArgumentException(this, "(0) String(not empty)");

		String sageVar = Sage.getSagePreVariable(term);

		StringBuilder sb = new StringBuilder();
		sb.append(sageVar);
		sb.append("f = ");
		sb.append(term);
		sb.append("; f.expand()");
		
		Object result = Sage.evaluateInCAS(sb.toString());
		return result;
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}
}
