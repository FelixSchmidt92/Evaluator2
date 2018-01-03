package de.uni_due.s3.evaluator2.core.function.linalg1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This function calculated the determinant of a matrix with Sage.
 * 
 * 
 * @author ??
 *
 */
public class Determinant extends Function {

	/**
	 * Expects a matrix as argument
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// check if argument is of type matrix or vector
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.LINALG2_MATRIX)
				&& !OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.LINALG2_VECTOR)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0) matrix");
		}
		return Sage.evaluateInCAS(getPartialSageSyntax(arguments));
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "(" + getSageSyntax(arguments.get(0)) + ").determinant()";
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
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "\\det{" + getLatexSyntax(arguments.get(0)) + "}";
	}

}
