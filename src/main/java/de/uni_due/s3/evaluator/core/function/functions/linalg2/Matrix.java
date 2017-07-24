package de.uni_due.s3.evaluator.core.function.functions.linalg2;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class Matrix extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException,
			CasEvaluationException, FunctionInvalidNumberOfArgumentsException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException, InvalidResultTypeException {
		for (Object arg : arguments) {
			if (!(OMTypeChecker.isOMAWithSymbol(arg, OMSymbol.LINALG2_MATRIXROW))) {
				throw new FunctionInvalidArgumentTypeException(this, "matrixrow");
			}
		}
		return OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, arguments);
	}

	@Override
	protected int minArgs() {
		return 0;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
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
