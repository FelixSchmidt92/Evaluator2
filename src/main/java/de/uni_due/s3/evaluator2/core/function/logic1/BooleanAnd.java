package de.uni_due.s3.evaluator2.core.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMS;

/**
 * Implements openmath logic and. Example true && true => true
 * 
 * @author frichtscheid
 *
 */
public class BooleanAnd extends Function {

	/**
	 * Tests if both given arguments are true. Expects two arguments of type
	 * OMSymbol.LOGIC_TRUE or LOGIC_FALSE
	 * 
	 * @return true or false as OMS
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {

		try {
			OMS arg1 = OMUtils.convertToLogicBoolean(OMUtils.convertOMToBoolean(arguments.get(0)));
			OMS arg2 = OMUtils.convertToLogicBoolean(OMUtils.convertOMToBoolean(arguments.get(1)));
			return (arg1.equals(OMSymbol.LOGIC1_TRUE) && arg2.equals(OMSymbol.LOGIC1_TRUE)) ? OMSymbol.LOGIC1_TRUE
					: OMSymbol.LOGIC1_FALSE;
		} catch (Exception e) {
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

		return getSageSyntax(arguments.get(0)) + " & " + getSageSyntax(arguments.get(1));
	}
}
