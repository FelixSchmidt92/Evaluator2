package de.uni_due.s3.evaluator2.function.integer1;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class Factorial extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		int number = getIntegerSyntax(arguments.get(0));
		int result = 1;

		if (number < 0) {
			throw new FunctionInvalidArgumentException(this, "The Value cannot be smaller 0");
		}

		for (int i = number; i != 0; i--) {
			if (i == 0) {
			}
			result = result * i;
		}

		return OMCreator.createOMI(result);
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "factorial(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
