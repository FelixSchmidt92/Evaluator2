package de.uni_due.s3.evaluator2.core.function.random_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Generates a random number between the specified min and max values ([min,max])
 * 
 * @author frichtscheid
 *
 */
public class RandomBetween extends Function{

	/**
	 * Expects two arguments:
	 * 	(1) integer or float
	 *  (2) integer or float
	 *  
	 * If the thrid argument is not given, it will return random float-Numbers
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		try {
			
			
			double min = OMUtils.convertOMToDouble(arguments.get(0));
			double max = OMUtils.convertOMToDouble(arguments.get(1));
			
			
			if (arguments.size() == 3 && OMUtils.convertOMToString(arguments.get(2)).equals("NN")) {
				return OMCreator.createOMI(	(int) Math.round(min+(Math.random()*(max-min))));
			}else {
				return OMCreator.createOMF(	min+(Math.random()*(max-min)));
			}
		}catch(InputMismatchException ie) {
			throw new FunctionInvalidArgumentTypeException(this, "(1) integer, float, double,  (2) integer, float, double");
		}
		
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 3;
	}

}
