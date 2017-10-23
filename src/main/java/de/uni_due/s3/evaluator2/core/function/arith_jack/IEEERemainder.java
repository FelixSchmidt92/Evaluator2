package de.uni_due.s3.evaluator2.core.function.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Computes the remainder operation on two arguments as prescribed by the IEEE
 * 754 standard. The remainder value is mathematically equal to f1 - f2 × n,
 * where n is the mathematical integer closest to the exact mathematical value
 * of the quotient f1/f2, and if two mathematical integers are equally close to
 * f1/f2, then n is the integer that is even. If the remainder is zero, its sign
 * is the same as the sign of the first argument.
 * 
 * @author spobel
 *
 */
public class IEEERemainder extends BinaryFunction {

	/**
	 * Expects two argument of type OMI
	 * 
	 * @return OMI
	 * @throws OpenMathException
	 * @throws EvaluatorException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws OpenMathException, EvaluatorException {

		Double leftValue = getDoubleSyntax(arguments.get(0));
		Double rightValue = getDoubleSyntax(arguments.get(1));
		if (rightValue == 0.0) {
			throw new FunctionInvalidArgumentException(this,
					"Second argument for IEEERemainder has to be unequal 0.0 .");
		}
		return OMCreator.createOMIOMF(Math.IEEEremainder(leftValue, rightValue));

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
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {

		return getBinaryLatex(arguments.get(0)) + "\\mathbin{\\%}" + getBinaryLatex(arguments.get(1));
	}
}
