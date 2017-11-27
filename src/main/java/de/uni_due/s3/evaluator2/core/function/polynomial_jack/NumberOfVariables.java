package de.uni_due.s3.evaluator2.core.function.polynomial_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Function to count number of different Variables in a term. Example: 2*x+4*a
 * -> 2; 2*x^3+4*x -> 1
 * 
 * @author spobel
 *
 */
public class NumberOfVariables extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Set<OMV> omvSet = getVariablesAsOMVSet(arguments.get(0));
		Integer size = omvSet.size();
		return OMCreator.createOMI(size);
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
