package de.uni_due.s3.evaluator2.core.function.transc1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Implements Sin function. Example sin(1) = 0.841470984807897, sin(0) = 0
 * 
 * @author spobel
 *
 */
public class Sin extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (!OMTypeChecker.isOMNumber(arguments.get(0))) {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
		}
		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));

		return result;
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
		return "sin(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
