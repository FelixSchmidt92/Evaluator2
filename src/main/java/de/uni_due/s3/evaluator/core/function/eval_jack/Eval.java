package de.uni_due.s3.evaluator.core.function.eval_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;

/**
 * Function does normal evaluation. This Function has to be deprecated.
 * 
 * @author spobel
 *
 */
public class Eval extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		return arguments.get(0);
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
