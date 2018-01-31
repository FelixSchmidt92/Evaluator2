package de.uni_due.s3.evaluator2.function.mc_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns MCIndex
 * 
 * @author spobel
 *
 */
public class MCIndex extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.MCJACK_MCINDEX, arguments);
	}
	
	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}

	@Override
	protected int minArgs() {
		return 4;
	}

	@Override
	protected int maxArgs() {
		return 4;
	}
	
	@Override
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getIntegerSyntax(arguments.get(0));
	}
	
	
}
