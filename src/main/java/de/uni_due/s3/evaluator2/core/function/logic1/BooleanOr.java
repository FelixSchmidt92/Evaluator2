package de.uni_due.s3.evaluator2.core.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;

/**
 * Implements openmath logic or. Example true | false => true
 * 
 * @author spobel
 */
public class BooleanOr extends BinaryFunction {

	public BooleanOr() {
		super(OMSPriority.getPriority(OMSymbol.LOGIC1_OR));
	}

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

		try {
			return (getBooleanSyntax(arguments.get(0)) || getBooleanSyntax(arguments.get(1))) ? OMSymbol.LOGIC1_TRUE
					: OMSymbol.LOGIC1_FALSE;
		} catch (NoRepresentationAvailableException e) {
			throw new FunctionInvalidArgumentTypeException(this, "boolean, int or float");
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

		return getSageSyntax(arguments.get(0)) + " | " + getSageSyntax(arguments.get(1));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {

		return arguments.get(0) + "\\mbox{or}" + arguments.get(1);
	}

}
