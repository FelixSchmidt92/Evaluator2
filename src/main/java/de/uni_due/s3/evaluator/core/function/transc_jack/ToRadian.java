package de.uni_due.s3.evaluator.core.function.transc_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Converts an angle measured in degrees to an approximately equivalent angle
 * measured in radians. The conversion from degrees to radians is generally
 * inexact.
 * 
 * @author spobel
 *
 */
public class ToRadian extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException,
			CasNotAvailableException, NoRepresentationAvailableException, CasEvaluationException,
			FunctionInvalidArgumentTypeException, OpenMathException, InvalidResultTypeException {
		try {
			Double value = OMUtils.convertOMToDouble(arguments.get(0));
			return OMUtils.convertDoubleToOMIOMF(Math.toRadians(value));
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer");
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
