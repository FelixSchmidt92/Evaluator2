package de.uni_due.s3.evaluator2.core.function.linalg1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class Determinant extends Function{

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		throw new FunctionNotImplementedException("functionality of determinant is not implemented yet");
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		
		return "\\det{"+getLatexSyntax(arguments.get(0))+"}";
	}

}
