package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function returns true if the containing vectors in this set are
 * linearlyIndependent, false otherwise.
 * 
 * @param A
 *            Set with Vectors in it.
 * 
 * @author dlux
 *
 */
public class IsLinearlyIndependent extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		List<Object> vectorList = getListSyntax(arguments.get(0));

		for (Object vector : vectorList) {
			if (!OMTypeChecker.isOMAWithSymbol(vector, OMSymbol.LINALG2_VECTOR)) {
				throw new FunctionInvalidArgumentException(this, " This Function expects a Set with n-Vector");
			}
		}

		// Create matrix
		OMA matrix = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, vectorList);

		List<Object> args = new ArrayList<>();
		args.add(matrix);
		String matrixSageSyntax = getSageSyntax(matrix);
		return Sage.evaluateInCAS(matrixSageSyntax + ".rank() == " + matrixSageSyntax + ".nrows() or "
				+ matrixSageSyntax + ".rank() == " + matrixSageSyntax + ".ncols()");
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
