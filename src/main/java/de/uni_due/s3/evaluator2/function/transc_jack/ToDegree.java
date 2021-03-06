package de.uni_due.s3.evaluator2.function.transc_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Converts an angle measured in radians to an approximately equivalent angle
 * measured in degrees. The conversion from radians to degrees is generally
 * inexact; users should not expect cos(toRadians(90.0)) to exactly equal 0.0.
 * 
 * @author spobel
 *
 */
public class ToDegree extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
			Double value = getDoubleSyntax(arguments.get(0));
			return OMCreator.createOMIOMF(Math.toDegrees(value));
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
