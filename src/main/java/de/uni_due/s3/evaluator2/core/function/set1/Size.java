package de.uni_due.s3.evaluator2.core.function.set1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
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
	protected Object execute(List<Object> arguments) throws FunctionException {

		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SET1_SET)
				&& !OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.LIST1_LIST)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Set|List");
		}
		OMA set = (OMA) arguments.get(0);

		OMI omi = new OMI();
		omi.setValue(Integer.toString(set.getOmel().size() - 1)); // -1
																	// wegen
																	// oms
		return omi;
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
