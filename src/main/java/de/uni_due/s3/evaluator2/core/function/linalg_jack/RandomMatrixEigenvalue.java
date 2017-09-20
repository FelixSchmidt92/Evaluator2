package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.List;
import java.util.regex.Pattern;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

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
	protected Object execute(List<Object> arguments) throws OpenMathException, EvaluatorException {

			String arg1 = getStringSyntax(arguments.get(0));
			int arg2 = getIntegerSyntax(arguments.get(1));
			String arg3 = getStringSyntax(arguments.get(2));
			String arg4 = getStringSyntax(arguments.get(3));

			if (!Pattern.matches("\\[([0-9]+,)*[0-9]+\\]", arg3) || !Pattern.matches("\\[([0-9]+,)*[0-9]+\\]", arg4)) {
				throw new FunctionInvalidArgumentException(this, "3. and 4. Arguments need to be like: [1,2,3,...]");
			}

			// check if arg1 is QQ RR ZZ
			if (!((arg1.equals("RR")) || (arg1.equals("QQ")) || (arg1.equals("ZZ")))) {
				throw new FunctionInvalidArgumentException(this, "The First Argument needs to be RR, QQ or ZZ");
			}

			return Sage.evaluateInCAS(
					"sage.matrix.constructor.random_diagonalizable_matrix(sage.matrix.matrix_space.MatrixSpace(" + arg1
							+ "," + arg2 + "," + arg2 + "), " + "eigenvalues=" + arg3 + ", dimensions=" + arg4 + ")");
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
