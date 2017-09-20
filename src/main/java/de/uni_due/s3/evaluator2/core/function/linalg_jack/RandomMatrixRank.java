package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * It creates a square Random Matrix with specific Rank.
 * 
 * @param1 String Number-Field (Either QQ or ZZ)
 * 
 * @param2 integer nrows of Matrix
 * 
 * @param3 Integer ncols of Matrix
 * 
 * @param4 Integer rank of Matrix(less then m or n)
 * 
 * @param5 [Integer] upper/lower Bound of Matrix (+/-Integer)
 * 
 * @author spobel
 *
 */
public class RandomMatrixRank extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String field = getStringSyntax(arguments.get(0));
		Integer m = getIntegerSyntax(arguments.get(1));
		Integer n = getIntegerSyntax(arguments.get(2));
		Integer rank = getIntegerSyntax(arguments.get(3));

		int maxMN = Math.max(m, n);
		if (rank > maxMN) {
			throw new FunctionInvalidArgumentException(this, "The third Argument is higher than (1) and (2)");
		}

		if (m < 0 || n < 0 || rank < 0) {
			throw new FunctionInvalidArgumentException(this, "Every Integer of this Function has to be positive or 0");
		}
		if (arguments.size() == 4) {
			return Sage.evaluateInCAS(
					"sage.matrix.constructor.random_echelonizable_matrix(sage.matrix.matrix_space.MatrixSpace(" + field
							+ ", " + m + ", " + n + "), rank=" + rank + ")");
		} else {
			return Sage.evaluateInCAS(
					"sage.matrix.constructor.random_echelonizable_matrix(sage.matrix.matrix_space.MatrixSpace(" + field
							+ ", " + m + ", " + n + "), rank=" + rank + ", upper_bound="
							+ getSageSyntax(arguments.get(4)) + ")");
		}
	}

	@Override
	protected int minArgs() {
		return 4;
	}

	@Override
	protected int maxArgs() {
		return 5;
	}

}
