package de.uni_due.s3.evaluator2.core.function.rounding1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * Returns the closest long to the argument, with ties rounding to positive
 * infinity.
 * 
 * @author spobel
 *
 */
public class Round extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		if (arguments.size()==1) {
			double param1 = getDoubleSyntax(arguments.get(0));
			double result = Math.round(param1);
			return OMCreator.createOMIOMF(result);
		} else {
			double param1 = getDoubleSyntax(arguments.get(0));
			double param2 = getIntegerSyntax(arguments.get(1));
			double factor = Math.pow(10, param2);
			double result = Math.round(param1*factor)/factor;
			return OMCreator.createOMIOMF(result);
		}
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

}
