package de.uni_due.s3.evaluator2.core.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;

/**
 * Implements openmath logic or. Example true | false => true
 * 
 * @author spobel
 */
public class BooleanOr extends BinaryFunction {

	/**
	 * Tests if one given argument is true. Expects two arguments of type
	 * OMSymbol.LOGIC_TRUE or LOGIC_FALSE
	 * 
	 * @return true or false as OMS
	 * @throws EvaluatorException
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		return (getBooleanSyntax(arguments.get(0)) || getBooleanSyntax(arguments.get(1))) ? OMSymbol.LOGIC1_TRUE
				: OMSymbol.LOGIC1_FALSE;
	}

	@Override
	public Object generatePalette(List<Object> arguments) throws FunctionNotImplementedException {
		return OMSymbol.LOGIC1_OR;
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

		return getSageSyntax(arguments.get(0)) + " | " + getSageSyntax(arguments.get(1));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		return getLatexSyntax(arguments.get(0)) + "\\mbox{or}" + getLatexSyntax(arguments.get(1));
	}

}
