package de.uni_due.s3.evaluator.core.function.cas_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

public class EvaluateInSage extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws CasEvaluationException,
			FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		return Sage.evaluateInCAS(getSageSyntax(arguments.get(0)));
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
