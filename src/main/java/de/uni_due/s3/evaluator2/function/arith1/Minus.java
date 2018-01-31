package de.uni_due.s3.evaluator2.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements arithmetic minus operation. Example 3-5 = -2
 * 
 * @author frichtscheid, spobel
 *
 */
public class Minus extends BinaryFunction {

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
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Double leftValue = getDoubleSyntax(arguments.get(0));
		Double rightValue = getDoubleSyntax(arguments.get(1));
		return OMCreator.createOMIOMF(leftValue - rightValue);
	}

	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.ARITH1_MINUS, arguments);
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "(( " + getSageSyntax(arguments.get(0)) + " ) - ( " + getSageSyntax(arguments.get(1)) + " ))";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getLatexSyntax(arguments.get(0)) + "-" + getLatexSyntax(arguments.get(1));
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getStringSyntax(arguments.get(0)) + "-" + getStringSyntax(arguments.get(1));
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "(( " + getRSyntax(arguments.get(0)) + " ) - ( " + getRSyntax(arguments.get(1)) + " ))";
	}
}
