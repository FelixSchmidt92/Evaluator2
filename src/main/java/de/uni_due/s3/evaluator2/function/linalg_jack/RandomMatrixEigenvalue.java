package de.uni_due.s3.evaluator2.function.linalg_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
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
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
			return Sage.evaluateInCAS(getPartialSageSyntax(arguments));
	}

	@Override
	protected int minArgs() {
		return 4;
	}

	@Override
	protected int maxArgs() {
		return 4;
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		StringBuilder sb = new StringBuilder();
		sb.append("sage.matrix.constructor.random_diagonalizable_matrix(sage.matrix.matrix_space.MatrixSpace(");
		sb.append(getSageSyntax(arguments.get(0)));
		sb.append(",");
		sb.append(getSageSyntax(arguments.get(1)));
		sb.append(",");
		sb.append(getSageSyntax(arguments.get(1)));
		sb.append("), eigenvalues=");
		sb.append(getSageSyntax(arguments.get(2)));
		sb.append(", dimensions=");
		sb.append(getSageSyntax(arguments.get(3)));
		sb.append(")");
		
		return   sb.toString();
	}

}
