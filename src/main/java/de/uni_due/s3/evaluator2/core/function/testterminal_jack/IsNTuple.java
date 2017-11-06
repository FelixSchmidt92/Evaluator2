package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMA;
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
public class IsNTuple extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws OpenMathException, EvaluatorException {
		int n = getIntegerSyntax(arguments.get(1));
		
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ECC_TUPLE)) {
			return OMSymbol.LOGIC1_FALSE;
		}
		
		OMA tuple = (OMA) arguments.get(0);
		
		if (tuple.getOmel().size() - 1 != n) {
			return OMSymbol.LOGIC1_FALSE;
		}	
		
		return OMSymbol.LOGIC1_TRUE;
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
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
