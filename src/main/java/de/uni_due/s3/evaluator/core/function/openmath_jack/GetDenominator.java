package de.uni_due.s3.evaluator.core.function.openmath_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Returns the Denominator of the Division (a/b --> returns b)
 * 
 * @author dlux
 *
 */
public class GetDenominator extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ARITH1_DIVIDE) && 
				!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.NUMS1_RATIONAL)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Division");
		}
		
		OMA oma = (OMA) arguments.get(0);
		try {
			if (OMUtils.convertOMToDouble(oma.getOmel().get(2)) == 0) {
				throw new FunctionInvalidArgumentException(this, "Denominator cannot be 0");
			}
		} catch (InputMismatchException e) {
			//Error could not Convert to OMI so return as usual
			return ((OMA) arguments.get(0)).getOmel().get(2);
		}
		return ((OMA) arguments.get(0)).getOmel().get(2);

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
