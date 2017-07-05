package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMOBJChildNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMObjectNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

public class Times extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentException {
		Object left = arguments.get(0);
		Object right = arguments.get(1);
		try {
			Double leftValue = NumberUtils.convertOMIOMFToDouble(left);
			Double rightValue = NumberUtils.convertOMIOMFToDouble(right);
			return NumberUtils.convertDoubleToOMIOMF(leftValue * rightValue);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentException(this, "integer, float, double");
		}
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws OMObjectNotSupportedException, OMOBJChildNotSupportedException, FunctionArgumentNumberException, NoRepresentationAvailableException{
		return getSageSyntax(arguments.get(0)) + " * " + getSageSyntax(arguments.get(1));
	}
	
}
