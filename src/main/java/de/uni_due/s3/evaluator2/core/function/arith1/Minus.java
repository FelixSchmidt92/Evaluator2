package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements arithmetic minus operation. Example 3-5 = -2
 * 
 * @author frichtscheid, spobel
 *
 */
public class Minus extends BinaryFunction {

	public Minus() {
		super(OMSPriority.getPriority(OMSymbol.ARITH1_MINUS));
	}

	/**
	 * Expects two arguments of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws CasEvaluationException
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws NoRepresentationAvailableException
	 * @throws CasNotAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws OpenMathException
	 * @throws EvaluatorException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws OpenMathException, EvaluatorException {
		try {
			Double leftValue = getDoubleSyntax(arguments.get(0));
			Double rightValue = getDoubleSyntax(arguments.get(1));
			return OMCreator.createOMIOMF(leftValue - rightValue);
		} catch (FunctionInvalidArgumentTypeException e) {
			if (OMTypeChecker.isOMV(arguments.get(0)) || OMTypeChecker.isOMV(arguments.get(1))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS,
							OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE, OMSymbol.ARITH1_POWER, OMSymbol.ARITH1_ROOT,
							OMSymbol.ARITH1_UNARY_MINUS)
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.ARITH1_PLUS, OMSymbol.ARITH1_MINUS,
							OMSymbol.ARITH1_TIMES, OMSymbol.ARITH1_DIVIDE, OMSymbol.ARITH1_POWER, OMSymbol.ARITH1_ROOT,
							OMSymbol.ARITH1_UNARY_MINUS)) {
				return OMCreator.createOMA(OMSymbol.ARITH1_MINUS, arguments); // In
																				// Termen
																				// für
																				// weitere
																				// Anwendung
																				// oder
																				// Darstellung
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "(" + getSageSyntax(arguments.get(0)) + " - " + getSageSyntax(arguments.get(1)) + ")";
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		
		return arguments.get(0) + "-" +arguments.get(1);
	}
	
	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return getStringSyntax(arguments.get(0)) + "-" + getStringSyntax(arguments.get(1));
	}

}
