package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openMath unary_minus for numbers Example: -3, -4.56
 * 
 * @author frichtscheid, spobel
 *
 */
public class UnaryMinus extends Function {

	/**
	 * Expects one argument of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws OpenMathException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, OpenMathException {
		try {
			Double value = OMUtils.convertOMToDouble(arguments.get(0));
			return OMUtils.convertDoubleToOMIOMF(value * -1);
		} catch (InputMismatchException e) {
			if (OMTypeChecker.isOMV(arguments.get(0)) || OMTypeChecker.isOMAWithSymbol(arguments.get(0),
					OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS, OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE,
					OMSymbol.ARITH1_POWER, OMSymbol.ARITH1_ROOT, OMSymbol.ARITH1_UNARY_MINUS)) {
				return OMCreator.createOMA(OMSymbol.ARITH1_UNARY_MINUS, arguments);
			}
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		return "-" + getSageSyntax(arguments.get(0));
	}

}
