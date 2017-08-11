package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

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
 * @author dlux
 *
 */
public class RandomMatrixRank extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		// Type Check
		if (!(arguments.get(0) instanceof OMSTR))
			throw new FunctionInvalidArgumentTypeException(this,
					"(0)String, (1)Integer, (2)Integer, (3)Integer, [(4)Integer]");
		for (int i = 1; i < arguments.size(); i++) {
			if (!(arguments.get(i) instanceof OMI))
				throw new FunctionInvalidArgumentTypeException(this,
						"(0)String, (1)Integer, (2)Integer, (3)Integer, [(4)Integer]");

			// Check if every Integer is >= 0
			int t = Integer.parseInt(((OMI) arguments.get(i)).getValue());
			if (t < 0)
				throw new FunctionInvalidArgumentException(this,
						"Every Integer of this Function has to be positive or 0");
		}
		// Check if field is QQ or ZZ
		OMSTR field = (OMSTR) arguments.get(0);
		if ((!field.getContent().equals("QQ") && !field.getContent().equals("ZZ")))
			throw new FunctionInvalidArgumentException(this, "The Field for this Function has to be either QQ or ZZ");

		// Check if r is smaller than n or m
		int maxNM = Math.max(Integer.parseInt(((OMI) arguments.get(1)).getValue()),
				Integer.parseInt(((OMI) arguments.get(2)).getValue()));
		int r = Integer.parseInt(((OMI) arguments.get(3)).getValue());
		if (r > maxNM)
			throw new FunctionInvalidArgumentException(this, "The third Argument is higher than (1) and (2)");

		// Calculating random Matrix with rank r
		if (arguments.size() == 4) {
			return Sage.evaluateInCAS(
					"sage.matrix.constructor.random_echelonizable_matrix(sage.matrix.matrix_space.MatrixSpace("
							+ field.getContent() + ", " + getSageSyntax(arguments.get(1)) + ", "
							+ getSageSyntax(arguments.get(2)) + "), rank=" + getSageSyntax(arguments.get(3)) + ")");
		} else {
			return Sage.evaluateInCAS(
					"sage.matrix.constructor.random_echelonizable_matrix(sage.matrix.matrix_space.MatrixSpace("
							+ field.getContent() + ", " + getSageSyntax(arguments.get(1)) + ", "
							+ getSageSyntax(arguments.get(2)) + "), rank=" + getSageSyntax(arguments.get(3))
							+ ", upper_bound=" + getSageSyntax(arguments.get(4)) + ")");
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
