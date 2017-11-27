package de.uni_due.s3.evaluator2.core.function.linalg2;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the creation of a matrix. Example:
 * matrix(matrixrow(1,2,3),matrixrow(4,5,6))
 * 
 * @author frichtscheid, spobel
 *
 */

public class Matrix extends ConstructorFunction {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		for (Object arg : arguments) {
			if (!(OMTypeChecker.isOMAWithSymbol(arg, OMSymbol.LINALG2_MATRIXROW))) {
				throw new FunctionInvalidArgumentTypeException(this, "matrixrow");
			}
		}
		return OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, arguments);
	}

	@Override
	protected int minArgs() {
		return 0;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String sageExpression = "matrix([";

		for (int i = 0; i < arguments.size(); i++) {
			if (i == 0) {
				sageExpression = sageExpression + getSageSyntax(arguments.get(i));
			} else {
				sageExpression = sageExpression + "," + getSageSyntax(arguments.get(i));
			}
		}

		return sageExpression + "])";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String begin = "\\left(\\begin{array}{";
		String args = "";

		for (int i = 0; i < arguments.size(); i++) {
			begin += "r";
			args += getLatexSyntax(arguments.get(i)) + "\\\\";
		}
		begin += "}";

		return begin + args + "\\end{array}\\right)";
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		List<Object> result = new ArrayList<>();
		result.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, omel));
		return result;
	}
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		//arguments should be oma of matrixrow
		int nrow,ncol;
		nrow = arguments.size();
		ncol = ((OMA) arguments.get(0)).getOmel().size()-1;
		StringBuilder sb = new StringBuilder();
		for(Object arg:arguments) {
			OMA row = (OMA) arg;
			for(int i = 1;i<row.getOmel().size();i++) {
				sb.append(getRSyntax(row.getOmel().get(i)));
				sb.append(',');
			}
		}
		sb.deleteCharAt(sb.length()-1);	//delete last ,
		
		return "matrix(c("+sb.toString()+"), nrow="+nrow+", ncol="+ncol+", byrow=TRUE";
	}

}
