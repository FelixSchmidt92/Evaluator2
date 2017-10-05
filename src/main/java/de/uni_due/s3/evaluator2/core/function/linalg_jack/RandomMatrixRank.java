package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
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
		return Sage.evaluateInCAS(getPartialSageSyntax(arguments));
	}

	@Override
	protected int minArgs() {
		return 4;
	}

	@Override
	protected int maxArgs() {
		return 5;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		StringBuilder sb = new StringBuilder();
		sb.append("sage.matrix.constructor.random_echelonizable_matrix(sage.matrix.matrix_space.MatrixSpace(");
		sb.append(getSageSyntax(arguments.get(0)));// field
		sb.append(", ");
		sb.append(getSageSyntax(arguments.get(1)));// m
		sb.append(", ");
		sb.append(getSageSyntax(arguments.get(2)));// n
		sb.append("), rank=");
		sb.append(getSageSyntax(arguments.get(3)));// rank

		if (arguments.size() == 5) {
			sb.append(", upper_bound=");
			sb.append(getSageSyntax(arguments.get(4)));// upperBound
		}
		sb.append(")");

		return sb.toString();
	}
}
