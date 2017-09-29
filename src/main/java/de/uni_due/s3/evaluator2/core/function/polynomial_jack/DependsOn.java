package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Tests if a given polynomial depends on a specified variable Example:
 * dependsOn("a+1","a") => true
 * 
 * @author spobel, frichtscheid
 *
 */
public class DependsOn extends Function {

	/**
	 * Expects two arguments.The first one is the polynomial (OMA), the
	 * second one should contain the dependent variable (OMSTR)
	 * @throws EvaluatorException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		if (!OMTypeChecker.isOMV(arguments.get(1))) {
			throw new FunctionInvalidArgumentTypeException(this, "(1)Char");
		}
		
		Set<OMV> variables = getVariablesAsOMVSet(arguments.get(0));
		boolean containsVariable = variables.contains(arguments.get(1));

		return containsVariable ? OMSymbol.LOGIC1_TRUE : OMSymbol.LOGIC1_FALSE;
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
