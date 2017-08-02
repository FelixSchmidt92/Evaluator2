package de.uni_due.s3.evaluator.core.function.linalg_jack;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

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
	protected Object execute(List<Object> arguments) throws FunctionException, NoRepresentationAvailableException,
			CasEvaluationException, CasNotAvailableException, OpenMathException {
		// Check if first argument is set
		if (!(OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SET1_SET)
				|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.LIST1_LIST))) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set/List");
		}

		OMA set = (OMA) arguments.get(0);
		List<Object> vectorList = set.getOmel();
		vectorList.remove(0);

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
