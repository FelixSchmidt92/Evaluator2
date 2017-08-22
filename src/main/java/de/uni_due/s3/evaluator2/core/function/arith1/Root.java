package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
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
	protected Object execute(List<Object> arguments) throws FunctionException, OpenMathException {
		try {
			if (arguments.size() == 1) {
				Double argValue = OMUtils.convertOMToDouble(arguments.get(0));
				return OMUtils.convertDoubleToOMIOMF(Math.sqrt(argValue));
			} else {
				Double argValue = OMUtils.convertOMToDouble(arguments.get(0));
				Double nth = OMUtils.convertOMToDouble(arguments.get(1));
				if (argValue < 0) {
					if (nth % 2 == 1) {
						Double result = -1 * Math.pow(Math.E, Math.log(Math.abs(argValue)) / nth);
						return OMUtils.convertDoubleToOMIOMF(result);
					}
				}
				return OMUtils.convertDoubleToOMIOMF(Math.pow(Math.E, Math.log(Math.abs(argValue)) / nth));
			}
		} catch (InputMismatchException e) {
			if (arguments.size() == 1) {
				if (OMTypeChecker.isOMV(arguments.get(0)) || OMTypeChecker.isOMAWithSymbol(arguments.get(0),
						OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS, OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE,
						OMSymbol.ARITH1_POWER, OMSymbol.ARITH1_ROOT, OMSymbol.ARITH1_UNARY_MINUS)) {
					return OMCreator.createOMA(OMSymbol.ARITH1_ROOT, arguments);
				}
			} else {
				if (OMTypeChecker.isOMV(arguments.get(0)) || OMTypeChecker.isOMV(arguments.get(1))
						|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS,
								OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE, OMSymbol.ARITH1_POWER,
								OMSymbol.ARITH1_ROOT, OMSymbol.ARITH1_UNARY_MINUS)
						|| OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS,
								OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE, OMSymbol.ARITH1_POWER,
								OMSymbol.ARITH1_ROOT, OMSymbol.ARITH1_UNARY_MINUS)) {
					return OMCreator.createOMA(OMSymbol.ARITH1_ROOT, arguments);
				}
			}
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer/Double/Float [(1)Integer]");
		}
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		if (arguments.size() == 1) {
			return "sqrt(" + getSageSyntax(arguments.get(0)) + ")";
		} else {
			return "(" + getSageSyntax(arguments.get(0)) + ").nth_root(" + getSageSyntax(arguments.get(1)) + ")";
		}
	}
	
	@Override
	public String getPartialLatexSyntax(List<String> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		
		if(arguments.size() == 1) {
			return "\\sqrt{" + arguments.get(0) + "}";
		} else {
			return "\\sqrt[" + arguments.get(1) + "]{"
				+ arguments.get(0) + "}";
		}
	}
}
