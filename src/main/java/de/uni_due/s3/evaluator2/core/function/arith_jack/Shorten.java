package de.uni_due.s3.evaluator2.core.function.arith_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Returns the greater of two double values.
 * 
 * @author spobel
 *
 */
public class Shorten extends Function {

	/**
	 * Returns the greater of two double values.
	 * 
	 * @throws EvaluatorException
	 * @throws OpenMathException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.NUMS1_RATIONAL)) {
			Object arg1 = ((OMA) arguments.get(0)).getOmel().get(1);
			Object arg2 = ((OMA) arguments.get(0)).getOmel().get(2);
			return Sage.evaluateInCAS(
					Sage.getSagePreVariable(arg1 + " " + arg2) + " ( " + getSageSyntax(arg1) + " )/( " + getSageSyntax(arg2) + " )");
		}
		return arguments.get(0);
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
