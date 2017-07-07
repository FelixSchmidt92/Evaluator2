package de.uni_due.s3.evaluator.core.function.functions.transc1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Implements ArcSin function. Example asin(1) = 1/2*pi = 1.57079632679490,
 * asin(0) = 0
 * 
 * @author spobel
 *
 */
public class ArcSin extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException,
			CasEvaluationException, FunctionInvalidNumberOfArgumentsException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException, InvalidResultTypeException {
		if (!OMTypeChecker.isOMFOrOMI(arguments.get(0))) {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
		}
		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));

		if (!OMTypeChecker.isOMFOrOMI(result)) {
			throw new InvalidResultTypeException(this, "integer, float, double");
		}
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

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		return "arcsin(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
