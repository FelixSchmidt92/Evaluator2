package de.uni_due.s3.evaluator2.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements division with integer and/or float Example: 4/3 = 1.33333...
 * 
 * @author frichtscheid, spobel
 *
 */
public class Divide extends BinaryFunction {

	/**
	 * It expects two arguments. Each argument has to be an OMI or OMF
	 * 
	 * @return OMI or OMF.
	 * @throws OpenMathException
	 * @throws EvaluatorException
	 * @throws FunctionInvalidArgumentException
	 */
	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Double leftValue = getDoubleSyntax(arguments.get(0));
		Double rightValue = getDoubleSyntax(arguments.get(1));
		if (rightValue == 0.0) {
			throw new FunctionInvalidArgumentException(this, "Second argument of Division '/' has to be unequal zero.");
		}
		return OMCreator.createOMIOMF(leftValue / rightValue);
	}

	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException {
		return OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, arguments);
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
		return "(( " + getSageSyntax(arguments.get(0)) + " ) / ( " + getSageSyntax(arguments.get(1)) + " ))";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "\\frac{" + getLatexSyntax(arguments.get(0)) + "}{" + getLatexSyntax(arguments.get(1)) + "}";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getStringSyntax(arguments.get(0)) + "/" + getStringSyntax(arguments.get(1));
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "(( " + getRSyntax(arguments.get(0)) + " ) / ( " + getRSyntax(arguments.get(1)) + " ))";
	}
}
