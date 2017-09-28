package de.uni_due.s3.evaluator2.core.function.linalg2;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class MatrixRow extends ConstructorFunction {

	@Override
	protected Object execute(List<Object> arguments) throws CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments);
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}
	
	@Override
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Boolean-representation for function " + this.getClass().getSimpleName());
	}

	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Double-representation for function " + this.getClass().getSimpleName());
	}

	@Override
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Integer-representation for function " + this.getClass().getSimpleName());
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no List-representation for function " + this.getClass().getSimpleName());
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		String sageExpression = "[";

		for (int i = 0; i < arguments.size(); i++) {
			if (i == 0) {
				sageExpression = sageExpression + getSageSyntax(arguments.get(i));
			} else {
				sageExpression = sageExpression + "," + getSageSyntax(arguments.get(i));
			}
		}

		return sageExpression + "]";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		String args = "";

		for (int i = 0; i < arguments.size(); i++) {
			args += getLatexSyntax(arguments.get(i)) + " & ";
		}
		args = args.substring(0, args.length() - 3); // remove last &

		return args;
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no R-representation for function " + this.getClass().getSimpleName());		
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no String-representation for function " + this.getClass().getSimpleName());
	}

}
