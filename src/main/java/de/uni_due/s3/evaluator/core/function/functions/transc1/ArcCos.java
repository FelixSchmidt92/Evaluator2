package de.uni_due.s3.evaluator.core.function.functions.transc1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.sage.Sage;

public class ArcCos extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException, CasNotAvailableException, NoRepresentationAvailableException, CasEvaluationException {
		return Sage.evaluateInCAS(getSageSyntax(arguments));
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		return getSageSyntax("arccos(" + getSageSyntax(arguments.get(0)) + ")");
	}
}
