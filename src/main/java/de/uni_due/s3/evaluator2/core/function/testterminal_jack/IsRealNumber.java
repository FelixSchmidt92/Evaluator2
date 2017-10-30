package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.sage.Sage;

/**
 * This Function tests if the first Argument can be casted to OMI|OMF, if it
 * does true will be returned (is a Number), false otherwise.
 * 
 * Note: PI and E are also Numbers so true will be also returned
 * 
 * @author spobel
 *
 */
public class IsRealNumber extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		String toTest = getSageSyntax(arguments.get(0));
		try {
			if (Sage.evaluateInCAS("isinstance(" + toTest + ", sage.rings.rational.Rational)").equals(OMSymbol.LOGIC1_TRUE)) {
				return OMSymbol.LOGIC1_TRUE;// Rational('1/3')
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			try {
				Sage.evaluateInCAS("Rational('" + toTest + "')");// Rational('1/3')
				return OMSymbol.LOGIC1_TRUE;
			} catch (Exception e1) {
				try {
					Sage.evaluateInCAS("RealNumber('" + toTest + "')");
					return OMSymbol.LOGIC1_TRUE;
				} catch (Exception e2) {
					try {
						if (OMSymbol.LOGIC1_TRUE.equals(Sage.evaluateInCAS(toTest + ".is_real()"))) {
							return OMSymbol.LOGIC1_TRUE;
						}
					} catch (Exception e3) {
					}
				}
			}
		}
		return OMSymbol.LOGIC1_FALSE;

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
