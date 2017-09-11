package de.uni_due.s3.evaluator2.core.function.integer1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class Factorial extends Function{

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		try {
			int number = OMUtils.convertOMToInteger(arguments.get(0));
			int result = 1;
			
			if (number < 0) {
				throw new FunctionInvalidArgumentException(this, "The Value cannot be smaller 0");
			}
			
			for(int i = number; i != 0; i-- ){
				if (i == 0) {}
				result = result*i;
			}
			
			return OMCreator.createOMI(result);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer");
		}
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "factorial(" +  getSageSyntax(arguments.get(0)) + ")";
	}

}
