package de.uni_due.s3.evaluator.core.function.set1;

import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Implements openmath set1 size operation Example: size({1,2}) = 2
 * 
 * @author frichtscheid
 *
 */
public class Size extends Function {

	/**
	 * Expects one argument of type OMA with set-OMS
	 * 
	 * @return size of the set
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {

		if (OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SET1_SET)) {
			OMA set = (OMA) arguments.get(0);

			OMI omi = new OMI();
			omi.setValue(Integer.toString(set.getOmel().size() - 1)); // -1
																		// wegen
																		// oms
			return omi;
		}

		throw new FunctionInvalidArgumentTypeException(this, "set");

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
