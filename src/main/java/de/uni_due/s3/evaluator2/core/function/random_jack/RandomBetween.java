package de.uni_due.s3.evaluator2.core.function.random_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Generates a random number between the specified min and max values
 * ([min,max])
 * 
 * @author frichtscheid
 *
 */
public class RandomBetween extends Function {

	/**
	 * Expects two arguments: (1) integer or float (2) integer or float
	 * 
	 * If the thrid argument is not given, it will return random float-Numbers
	 * @throws EvaluatorException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
			double min = getDoubleSyntax(arguments.get(0));
			double max = getDoubleSyntax(arguments.get(1));

			return OMCreator.createOMF(min + (Math.random() * (max - min)));
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
