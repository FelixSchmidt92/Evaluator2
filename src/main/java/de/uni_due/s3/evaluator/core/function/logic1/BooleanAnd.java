package de.uni_due.s3.evaluator.core.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

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
	protected Object execute(List<Object> arguments) throws FunctionException {
		OMS arg1, arg2;
		String omsCd = OMSymbol.LOGIC1_FALSE.getCd();
		String omsTrue = OMSymbol.LOGIC1_TRUE.getName();
		String omsFalse = OMSymbol.LOGIC1_FALSE.getName();

		// type check
		if (OMTypeChecker.isOMS(arguments.get(0)) && OMTypeChecker.isOMS(arguments.get(1))) {
			arg1 = (OMS) arguments.get(0);
			arg2 = (OMS) arguments.get(1);
			// semantic check
			if (arg1.getCd().equals(omsCd) && arg2.getCd().equals(omsCd)) {
				if ((arg1.getName().equals(omsTrue) || arg1.getName().equals(omsFalse))
						&& (arg2.getName().equals(omsTrue) || arg2.getName().equals(omsFalse))) {
					if (arg1.equals(OMSymbol.LOGIC1_TRUE) && arg2.equals(OMSymbol.LOGIC1_TRUE))
						return OMSymbol.LOGIC1_TRUE;
					else
						return OMSymbol.LOGIC1_FALSE;
				}
			}
		}

		throw new FunctionInvalidArgumentTypeException(this, "boolean");
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {

		return getSageSyntax(arguments.get(0)) + " & " + getSageSyntax(arguments.get(1));
	}
}
