package de.uni_due.s3.evaluator.core.function.functions.linalg_jack;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;
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
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException,
			FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException,
			FunctionInvalidArgumentException, CasEvaluationException, CasNotAvailableException, OpenMathException {
		// Check if first argument is set
		if (!(arguments.get(0) instanceof OMA)
				|| (!((OMA) arguments.get(0)).getOmel().get(0).equals(OMSymbol.SET1_SET))) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set");
		}

		OMA oma = (OMA) arguments.get(0);

		ArrayList<Object> omel = new ArrayList<>();
		for (int i = 1; i < oma.getOmel().size(); i++) {
			// check if first argument in set is vector
			if (!(oma.getOmel().get(i) instanceof OMA)
					|| (!((OMA) oma.getOmel().get(i)).getOmel().get(0).equals(OMSymbol.LINALG2_VECTOR))) {
				throw new FunctionInvalidArgumentException(this, " This Function expects a Set with n-Vector");
			}
			omel.add(oma.getOmel().get(i));
		}

		// Create matrix
		OMA matrix = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, omel);

		return Sage.evaluateInCAS(getSageSyntax(matrix) + ".rank() == " + getSageSyntax(matrix) + ".nrows() or "
				+ getSageSyntax(matrix) + ".rank() == " + getSageSyntax(matrix) + ".ncols()");
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
