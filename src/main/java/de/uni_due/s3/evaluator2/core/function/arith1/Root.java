package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the correctly rounded positive nth root of a double value. If there
 * is only one param it returns sqrt.
 * 
 * @author spobel
 *
 */
public class Root extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (arguments.size() == 1) {
			Double argValue =getDoubleSyntax(arguments.get(0));
			return OMCreator.createOMIOMF(Math.sqrt(argValue));
		} else {
			Double argValue = getDoubleSyntax(arguments.get(0));
			Double nth = getDoubleSyntax(arguments.get(1));
			if (argValue < 0) {
				if (nth % 2 == 1) {
					Double result = -1 * Math.pow(Math.E, Math.log(Math.abs(argValue)) / nth);
					return OMCreator.createOMIOMF(result);
				}
			}
			return OMCreator.createOMIOMF(Math.pow(Math.E, Math.log(Math.abs(argValue)) / nth));
		}
	}

	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException {
		return OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, arguments);
	}
	
	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (arguments.size() == 1) {
			return "sqrt(" + getSageSyntax(arguments.get(0)) + ")";
		} else {
			return "(pow((" + getSageSyntax(arguments.get(0)) + "), 1/(" + getSageSyntax(arguments.get(1)) + ")))";
		}
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		
		if(arguments.size() == 1) {
			return "\\sqrt{" + getLatexSyntax(arguments.get(0))  + "}";
		} else {
			return "\\sqrt[" + getLatexSyntax(arguments.get(1))  + "]{"
				+ getLatexSyntax(arguments.get(0)) + "}";
		}
	}
}
