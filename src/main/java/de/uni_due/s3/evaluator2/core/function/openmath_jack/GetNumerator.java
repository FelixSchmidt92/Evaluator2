package de.uni_due.s3.evaluator2.core.function.openmath_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Returns the Nominator of the Division (a/b --> returns a)
 * 
 * @author dlux
 *
 */
public class GetNumerator extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.ARITH1_DIVIDE)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Division");
		}
		
		OMA oma = (OMA) arguments.get(0);
		try {
			if (OMUtils.convertOMToDouble(oma.getOmel().get(2)) == 0) {
				throw new FunctionInvalidArgumentException(this, "Denominator cannot be 0");
			}
		} catch (InputMismatchException e) {
			//Error could not Convert to OMI so return as usual
			return ((OMA) arguments.get(0)).getOmel().get(1);
		}
		return ((OMA) arguments.get(0)).getOmel().get(1);
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
