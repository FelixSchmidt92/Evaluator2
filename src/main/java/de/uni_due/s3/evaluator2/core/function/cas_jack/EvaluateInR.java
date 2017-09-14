package de.uni_due.s3.evaluator2.core.function.cas_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;

public class EvaluateInR extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
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
