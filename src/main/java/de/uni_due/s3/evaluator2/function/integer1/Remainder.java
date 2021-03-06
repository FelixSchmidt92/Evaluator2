package de.uni_due.s3.evaluator2.function.integer1;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements a modulus operation. Example: 3%5 = 3
 * 
 * @author frichtscheid
 *
 */
public class Remainder extends BinaryFunction {

	/**
	 * Expects two argument of type OMI
	 * 
	 * @return OMI
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws OpenMathException
	 * @throws EvaluatorException
	 */
	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		double left = getIntegerSyntax(arguments.get(0));
		double right = getIntegerSyntax(arguments.get(1));

		if (right == 0) {
			throw new FunctionInvalidArgumentException(this, "The Second Argument cannot be 0");
		}

		return OMCreator.createOMIOMF(left % right);
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
		return getSageSyntax(arguments.get(0)) + " % " + getSageSyntax(arguments.get(1));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getLatexSyntax(arguments.get(0)) + "\\mathbin{\\%}" + getLatexSyntax(arguments.get(1));
	}

}
