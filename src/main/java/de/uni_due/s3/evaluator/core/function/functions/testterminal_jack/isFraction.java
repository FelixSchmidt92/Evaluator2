package de.uni_due.s3.evaluator.core.function.functions.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function just checks if the given argument is an OMA containing the OMS
 * Rational.
 * 
 * Note: This Function does not evaluate the inner functions, due in Evaluator1
 * '/' was used to set Fractions. So this Function checks for both.
 * 
 * @author dlux
 *
 */
public class isFraction extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		if (!(arguments.get(0) instanceof OMA)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Application(Rational)");
		}

		
		//check for size, because it is unevaluated
		OMA oma = (OMA) arguments.get(0);
		if (oma.getOmel().size() == 3 && oma.getOmel().get(0).equals(OMSymbol.NUMS1_RATIONAL)) {
			return OMSymbol.LOGIC1_TRUE;
		}

		if (oma.getOmel().size() == 3 && oma.getOmel().get(0).equals(OMSymbol.ARITH1_DIVIDE)) {
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
