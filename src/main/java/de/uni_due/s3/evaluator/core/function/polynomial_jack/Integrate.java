package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.PolyUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

public class Integrate extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws CasEvaluationException,
			FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
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
	public String getPartialSageSyntax(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
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
