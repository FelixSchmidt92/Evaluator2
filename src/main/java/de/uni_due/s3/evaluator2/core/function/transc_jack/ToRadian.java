package de.uni_due.s3.evaluator2.core.function.transc_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
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
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
			Double value = getDoubleSyntax(arguments.get(0));
			return OMCreator.createOMIOMF(Math.toRadians(value));
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		
		return "\\mbox{toRadian}\\left("+getLatexSyntax(arguments.get(0))+"\\degree\\right)";
	}

}
