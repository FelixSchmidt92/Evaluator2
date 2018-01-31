package de.uni_due.s3.evaluator2.function.transc1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements Sin function. Example sin(1) = 0.841470984807897, sin(0) = 0
 * 
 * @author spobel
 *
 */
public class Sin extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (OMTypeChecker.isOMNumber(arguments.get(0))) {
			return Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		} else {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double, variable");
		}
	}

	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.TRANSC1_SIN, arguments);
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
		return "sin(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
