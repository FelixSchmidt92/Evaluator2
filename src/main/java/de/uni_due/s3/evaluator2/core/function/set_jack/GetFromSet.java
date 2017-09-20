package de.uni_due.s3.evaluator2.core.function.set_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;

/**
 * Implements a get Function for Set (List).
 * 
 * Returns element at index
 * 
 * @author spobel
 *
 */
public class GetFromSet extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		int pos = getIntegerSyntax(arguments.get(0));
		List<Object> list1 = getListSyntax(arguments.get(1));

		if (list1.size() == 0) {
			throw new FunctionInvalidArgumentException(this, "Set has to have at least one element.");
		}

		if (list1.size() <= pos) {
			throw new FunctionInvalidArgumentException(this,
					"Second Argument of getFromSet is invalid. Not in Range of Set.");
		}

		return list1.get(pos);
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
