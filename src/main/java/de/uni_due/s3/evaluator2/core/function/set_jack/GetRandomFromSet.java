package de.uni_due.s3.evaluator2.core.function.set_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Implements a get Function for Set (List).
 * 
 * Random Elment of Set
 * 
 * @author spobel
 *
 */
public class GetRandomFromSet extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.SET1_SET)) {
			throw new FunctionInvalidArgumentTypeException(this, "(0)Integer, (1)Set");
		}
		List<Object> set = ((OMA) arguments.get(0)).getOmel();
		set.remove(0); // 0te element ist die OMS_SET1_SET
		
		if (set.size() == 0) {
			throw new FunctionInvalidArgumentException(this, "Set has to have at least one element.");
		}
		
		Integer pos = new java.util.Random().nextInt(set.size());
		
		return set.get(pos); 
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
