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
 * Implements Cos function. Example cos(1) = 0.540302305868140, cos(0) = 1
 * 
 * @author spobel
 *
 */
public class Cos extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (OMTypeChecker.isOMNumber(arguments.get(0))) {
			String sageString = getPartialSageSyntax(arguments);
			return Sage.evaluateInCAS(Sage.getSagePreVariable(sageString) + sageString);
		} else {
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double, variable");
		}
	}
	
	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.TRANSC1_COS, arguments);
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
		return "cos(" + getSageSyntax(arguments.get(0)) + ")";
	}

}
