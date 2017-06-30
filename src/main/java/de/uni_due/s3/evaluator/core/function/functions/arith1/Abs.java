package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.FunctionArgumentMismatchException;
import de.uni_due.s3.evaluator.exceptions.InputMismatchException;

public class Abs extends Function{

	
	@Override
	protected Object execute(List<Object> arguments) {
		Object arg = arguments.get(0);
		try {
			Double argValue = NumberUtils.convertOMIOMFToDouble(arg);
			return NumberUtils.convertDoubleToOMIOMF(Math.abs(argValue));
		} catch (InputMismatchException e) {
			throw new FunctionArgumentMismatchException("Function abs accepts only integer, float, double values.");
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
	public String getPartialSageSyntax(List<Object> arguments) {
		return "abs("+getSageSyntax(arguments)+")";
	}

}
