package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * @Symja This Function works (mostly) as in Symja
 * 
 *        This Function checks if in given Expression the given Variable (or
 *        Number) is a Polynom
 * 
 * @author dlux
 *
 */
public class IsTuple extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ECC_TUPLE)) {
			return OMSymbol.LOGIC1_TRUE;
		}
		return OMSymbol.LOGIC1_FALSE;
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
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
