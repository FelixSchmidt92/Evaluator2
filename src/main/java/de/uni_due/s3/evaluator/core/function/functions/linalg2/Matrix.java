package de.uni_due.s3.evaluator.core.function.functions.linalg2;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.LinalgUtils;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

public class Matrix extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException,
			CasEvaluationException, FunctionInvalidNumberOfArgumentsException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException, InvalidResultTypeException {
		for (Object arg : arguments) {
			if (!(LinalgUtils.isOMAwithOMS(arg, OMSymbol.LINALG2_MATRIXROW))) {
				throw new FunctionInvalidArgumentTypeException(this, "matrixrow");
			}
		}

		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));

		if (!(LinalgUtils.isOMAwithOMS(result, OMSymbol.LINALG2_MATRIX))) {
			throw new InvalidResultTypeException(this, "matrix");
		}
		return result;
	}

	@Override
	protected int minArgs() {
		return -1;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		String sageExpression = "matrix([";

		for (int i = 0; i < arguments.size(); i++) {
			if (i == 0) {
				sageExpression = sageExpression + getSageSyntax(arguments.get(i));
			} else {
				sageExpression = sageExpression + "," + getSageSyntax(arguments.get(i));
			}
		}

		return sageExpression + "])";
	}

}
