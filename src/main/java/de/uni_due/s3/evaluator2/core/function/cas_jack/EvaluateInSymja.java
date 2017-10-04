package de.uni_due.s3.evaluator2.core.function.cas_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;

public class EvaluateInSymja extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionNotImplementedException {
		throw new FunctionNotImplementedException("Evaluate in Symja is not implemented");
	}

	@Override
	protected int minArgs() {
		return 0;
	}

	@Override
	protected int maxArgs() {
		return 0;
	}

}
