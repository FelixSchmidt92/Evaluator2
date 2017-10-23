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
		try {
			Sage.evaluateInCAS("Rational('" + getSageSyntax(arguments.get(0)) + "')");// Rational('1/3')
			return OMSymbol.LOGIC1_TRUE;
		} catch (Exception e) {
			try {
				Sage.evaluateInCAS("RealNumber('" + getSageSyntax(arguments.get(0)) + "')");
				return OMSymbol.LOGIC1_TRUE;
			} catch (Exception e1) {
				try {
					if (OMSymbol.LOGIC1_TRUE
							.equals(Sage.evaluateInCAS(getSageSyntax(arguments.get(0)) + ".is_real()"))) {
						return OMSymbol.LOGIC1_TRUE;
					}
				} catch (Exception e2) {
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
