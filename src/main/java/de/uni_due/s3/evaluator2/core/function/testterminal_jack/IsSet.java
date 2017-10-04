package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * This class tests if the following Argument is a set. It returns iff its a
 * set, false otherwise.
 * 
 * @author dlux
 *
 */
public class IsSet extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		Object obj = arguments.get(0);
		if (OMTypeChecker.isOMAWithSymbol(obj, OMSymbol.SET1_SET, OMSymbol.LIST1_LIST)) {
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

}
