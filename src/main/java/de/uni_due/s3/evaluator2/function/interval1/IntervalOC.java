package de.uni_due.s3.evaluator2.function.interval1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Represents an Openmath representation for the integer_interval from cd <a
 * href="www.openmath.org/cd/interval1.xhtml>interval1</a>.
 * 
 * @author frichtscheid
 *
 */
public class IntervalOC extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Integer argRangeStart = getIntegerSyntax(arguments.get(0));
		Integer argRangeEnd = getIntegerSyntax(arguments.get(1));
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(argRangeStart));
		args.add(OMCreator.createOMI(argRangeEnd));

		return OMCreator.createOMA(OMSymbol.INTERVAL1_INTERVALOC, args);
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {

		return "\\\\left(" + getLatexSyntax(arguments.get(0)) + "," + getLatexSyntax(arguments.get(1)) + "\\\\right]";
	}

}
