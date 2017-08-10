package de.uni_due.s3.evaluator.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements division with integer and/or float Example: 4/3 = 1.33333...
 * 
 * @author frichtscheid, spobel
 *
 */
public class Divide extends Function {

	/**
	 * It expects two arguments. Each argument has to be an OMI or OMF
	 * 
	 * @return OMI or OMF.
	 * @throws OpenMathException
	 * @throws FunctionInvalidArgumentException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, OpenMathException {
		try {
			Double leftValue = OMUtils.convertOMToDouble(arguments.get(0));
			Double rightValue = OMUtils.convertOMToDouble(arguments.get(1));
			if (rightValue == 0.0) {
				throw new FunctionInvalidArgumentException(this,
						"Second argument of Division / has to be unequal zero.");
			}
			return OMUtils.convertDoubleToOMIOMF(leftValue / rightValue);
		} catch (InputMismatchException e) {
			if (OMTypeChecker.isOMV(arguments.get(0)) || OMTypeChecker.isOMV(arguments.get(1))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS,
							OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE, OMSymbol.ARITH1_POWER, OMSymbol.ARITH1_ROOT,
							OMSymbol.ARITH1_UNARY_MINUS)
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS,
							OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE, OMSymbol.ARITH1_POWER, OMSymbol.ARITH1_ROOT,
							OMSymbol.ARITH1_UNARY_MINUS)) {
				return OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, arguments);
			}
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		return getSageSyntax(arguments.get(0)) + " / " + getSageSyntax(arguments.get(1));
	}

}
