package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.FunctionArgumentMismatchException;
import de.uni_due.s3.evaluator.exceptions.InputMismatchException;

public class Times extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		Object left = arguments.get(0);
		Object right = arguments.get(1);
		try {
			Double leftValue = NumberUtils.convertOMIOMFToDouble(left);
			Double rightValue = NumberUtils.convertOMIOMFToDouble(right);
			return NumberUtils.convertDoubleToOMIOMF(leftValue * rightValue);
		} catch (InputMismatchException e) {
			throw new FunctionArgumentMismatchException("Operator '*' accepts only integer, float, double values.");
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

}
