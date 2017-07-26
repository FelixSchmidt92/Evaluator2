package de.uni_due.s3.evaluator.core.function.linalg_jack;

import java.util.List;
import java.util.regex.Pattern;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * @param OMSTR
 *            Sage-Number-Field QQ, RR or ZZ
 * 
 * @param OMI
 *            Matrix size nxn
 * 
 * @param MatrixRow|Vector
 *            eigenvalues
 * 
 * @param MatrixRow|Vector
 *            dimension (index of eigenvalues and dimension is same)
 * @author spobel
 *
 */
public class RandomMatrixEigenvalue extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException,
			CasEvaluationException, FunctionInvalidNumberOfArgumentsException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException, FunctionInvalidArgumentException {

		try {
			String arg1 = OMUtils.convertOMToString(arguments.get(0));
			int arg2 = OMUtils.convertOMIToInteger(arguments.get(1));
			String arg3 = OMUtils.convertOMToString(arguments.get(2));
			String arg4 = OMUtils.convertOMToString(arguments.get(3));

			if (!OMTypeChecker.isOMSTR(arguments.get(0)) || !OMTypeChecker.isOMI(arguments.get(1))) {
				throw new InputMismatchException();
			}
			if (!Pattern.matches("\\[([0-9]+,)*[0-9]+\\]", arg3) || !Pattern.matches("\\[([0-9]+,)*[0-9]+\\]", arg4)) {
				throw new InputMismatchException();
			}

			// check if arg1 is QQ RR ZZ
			if (!((arg1.equals("RR")) || (arg1.equals("QQ")) || (arg1.equals("ZZ")))) {
				throw new FunctionInvalidArgumentException(this, "The First Argument needs to be RR, QQ or ZZ");
			}

			return Sage.evaluateInCAS(
					"sage.matrix.constructor.random_diagonalizable_matrix(sage.matrix.matrix_space.MatrixSpace(" + arg1
							+ "," + arg2 + "," + arg2 + "), " + "eigenvalues=" + arg3 + ", dimensions=" + arg4 + ")");

		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this,
					"(0)String, (1)Integer, (2)SageSyntax[a,b,c], (3)SageSyntax[a,b,c]");
		}
	}

	@Override
	protected int minArgs() {
		return 4;
	}

	@Override
	protected int maxArgs() {
		return 4;
	}

}
