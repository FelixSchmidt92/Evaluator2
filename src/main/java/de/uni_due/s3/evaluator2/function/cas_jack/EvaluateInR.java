package de.uni_due.s3.evaluator2.function.cas_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.r.R;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class EvaluateInR extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return R.evaluateInCAS(getRSyntax(arguments.get(0)));
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
