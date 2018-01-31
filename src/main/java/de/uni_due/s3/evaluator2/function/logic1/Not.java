package de.uni_due.s3.evaluator2.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implement openmath logic not. Example: !true => false
 * 
 * @author spobel
 *
 */
public class Not extends BinaryFunction {

	/**
	 * Takes one boolean argument and returns the opposite value. Expects one
	 * argument of type OMSymbol.LOGIC_TRUE or LOGIC_FALSE
	 * 
	 * @return true or false as OMS
	 * @throws EvaluatorException
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return (getBooleanSyntax(arguments.get(0))) ? OMSymbol.LOGIC1_FALSE : OMSymbol.LOGIC1_TRUE;
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "not(" + getLatexSyntax(arguments.get(0)) + ")";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "\\neg" + getLatexSyntax(arguments.get(0));
	}
}
