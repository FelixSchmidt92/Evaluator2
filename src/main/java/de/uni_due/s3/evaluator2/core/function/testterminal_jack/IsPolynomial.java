package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * @Symja This Function works (mostly) as in Symja
 * 
 *        This Function checks if in given Expression the given Variable (or
 *        Number) is a Polynom
 * 
 * @author dlux
 *
 */
public class IsPolynomial extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String term = getSageSyntax(arguments.get(0));
		String var = getSageSyntax(arguments.get(1));

		if (var.length() != 1) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Term, (1)Char");
		}

		String sageVar = Sage.getSagePreVariable(term + " " + var);

		StringBuilder sb = new StringBuilder();

		sb.append(sageVar);
		sb.append("SR(");
		sb.append(term);
		sb.append(").is_polynomial(");
		sb.append(var);
		sb.append(")");

		Object result = Sage.evaluateInCAS(sb.toString());
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
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}

}
