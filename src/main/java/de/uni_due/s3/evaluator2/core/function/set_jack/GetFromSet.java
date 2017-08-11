package de.uni_due.s3.evaluator2.core.function.set_jack;

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
 * Implements a get Function for Set (List).
 * 
 * @author spobel
 *
 */
public class GetFromSet extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.SET1_SET)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer, (1)Set");
		}
		try {
			int pos = OMUtils.convertOMToInteger(arguments.get(0));
			List<Object> set = ((OMA) arguments.get(1)).getOmel();
			set.remove(0); // OMS entfernen

			if (set.size() == 0) {
				throw new FunctionInvalidArgumentException(this, "Set has to have at least one element.");
			}

			if (set.size() <= pos) {
				throw new FunctionInvalidArgumentException(this,
						"Second Argument of getFromSet is invalid. Not in Range of Set.");
			}

			return set.get(pos); // 0te element ist die OMS_SET1_SET
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer, (1)Set");
		}
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

}
