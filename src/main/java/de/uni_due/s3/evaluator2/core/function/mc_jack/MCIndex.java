package de.uni_due.s3.evaluator2.core.function.mc_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
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
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.MCJACK_MCINDEX, arguments));
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
}
