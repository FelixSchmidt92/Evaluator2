package de.uni_due.s3.evaluator2.core.function.rounding_jack;

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
 * This Function returns the rounded value of an double (except for Infinity and NaN)
 * 
 * @author dlux
 *
 */
public class Rint extends Function{

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		try {
			double arg = OMUtils.convertOMToDouble(arguments.get(0));
			if(Double.isInfinite(arg) || Double.isNaN(arg)) {
				return OMCreator.createOMF(Math.rint(arg));
			}
			return OMCreator.createOMI((int)Math.rint(arg));
			
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException("(0)Integer/Double");
		}
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
