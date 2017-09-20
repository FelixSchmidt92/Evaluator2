package de.uni_due.s3.evaluator2.core.function.set_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;

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
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		List<Object> set = getListSyntax(arguments.get(0));
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
