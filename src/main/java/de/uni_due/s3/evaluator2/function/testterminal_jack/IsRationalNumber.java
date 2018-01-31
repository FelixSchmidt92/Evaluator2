package de.uni_due.s3.evaluator2.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function tests if the first Argument can be casted to OMI|OMF, if it
 * does true will be returned (is a Number), false otherwise.
 * 
 * Note: PI and E are also Numbers so true will be also returned
 * 
 * @author spobel
 *
 */
public class IsRationalNumber extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String toTest = getSageSyntax(arguments.get(0));
		try {
			if (Sage.evaluateInCAS("isinstance(" + toTest + ", sage.rings.rational.Rational)")
					.equals(OMSymbol.LOGIC1_TRUE)) {
				return OMSymbol.LOGIC1_TRUE;// Rational('1/3')
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			try {
				Sage.evaluateInCAS("Rational('" + toTest + "')");// Rational('1/3')
			} catch (Exception e1) {
				try {
					Sage.evaluateInCAS("Rational(RealNumber('" + toTest + "'))"); // Rational(1.1)
				} catch (Exception e2) {
					return OMSymbol.LOGIC1_FALSE;
				}
			}
		}
		return OMSymbol.LOGIC1_TRUE;
	}

	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
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
