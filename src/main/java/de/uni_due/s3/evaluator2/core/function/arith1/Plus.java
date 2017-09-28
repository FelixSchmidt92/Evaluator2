package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements plus operation for numbers Example: 3.56 + 4 = 7.56
 * 
 * @author frichtscheid, spobel
 *
 */
public class Plus extends BinaryFunction {

	public Plus() {
		super(OMSPriority.getPriority(OMSymbol.ARITH1_PLUS));
	}

	/**
	 * @throws OpenMathException
	 *             Expects two arguments of type OMI or OMF
	 * @throws EvaluatorException 
	 * @throws NoRepresentationAvailableException
	 * @throws CasNotAvailableException
	 * @throws FunctionInvalidNumberOfArgumentsException
	 * @throws CasEvaluationException
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws OpenMathException, EvaluatorException {
		// evaluate this method in sage
		try {
			Double leftValue = getDoubleSyntax(arguments.get(0));
			Double rightValue = getDoubleSyntax(arguments.get(1));
			return OMCreator.createOMIOMF(leftValue + rightValue);
		} catch (FunctionInvalidArgumentTypeException e) {
			if (OMTypeChecker.isOMV(arguments.get(0)) || OMTypeChecker.isOMV(arguments.get(1))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SYMBOLIC_EXPRESSION)
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.SYMBOLIC_EXPRESSION)) {
				return OMCreator.createOMA(OMSymbol.ARITH1_PLUS, arguments);
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
		return "(" + getSageSyntax(arguments.get(0)) + " + " + getSageSyntax(arguments.get(1)) + ")";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {

		return arguments.get(0) + "+" + arguments.get(1);
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return getStringSyntax(arguments.get(0)) + "+" + getStringSyntax(arguments.get(1));
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException {
		return "(" + getRSyntax(arguments.get(0)) + " + " + getRSyntax(arguments.get(1)) + ")";
	}

}
