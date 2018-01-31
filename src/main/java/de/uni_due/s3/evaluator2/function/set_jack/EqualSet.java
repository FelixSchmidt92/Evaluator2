package de.uni_due.s3.evaluator2.function.set_jack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements equalSet Function
 * 
 * @author frichtscheid
 *
 */
public class EqualSet extends Function {

	/**
	 * Tests if two given objects are equal by using the object-equal method.
	 * Expects 2 arguments of any type
	 * 
	 * @throws FunctionInvalidArgumentTypeException
	 * 
	 */
	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		List<Object> firstList = getListSyntax(arguments.get(0));
		List<Object> secondList = getListSyntax(arguments.get(1));
		Set<Object> firstSet = new HashSet<>(firstList);
		Set<Object> secondSet = new HashSet<>(secondList);

		return (firstSet.equals(secondSet)) ? OMSymbol.LOGIC1_TRUE : OMSymbol.LOGIC1_FALSE;
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}
}
