package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator2.core.PolyUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Function to count number of different Variables in a term. Example: 2*x+4*a
 * -> 2; 2*x^3+4*x -> 1
 * 
 * @author spobel
 *
 */
public class NumberOfVariables extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		Set<String> setString = PolyUtils.getVariables(getSageSyntax(arguments.get(0)));
		return OMCreator.createOMI(setString.size());
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

}
