package de.uni_due.s3.evaluator2.core.function.linalg2;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the creation of a matrix. Example:
 * matrix(matrixrow(1,2,3),matrixrow(4,5,6))
 * 
 * @author frichtscheid, spobel
 *
 */

public class Matrix extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
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

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		String begin = "\\left(\\begin{array}{";
		String args = "";

		for (int i = 0; i < arguments.size(); i++) {
			begin += "r";
			args += getLatexSyntax(arguments.get(i)) + "\\\\";
		}
		begin += "}";

		return begin + args + "\\end{array}\\right)";
	}

}
