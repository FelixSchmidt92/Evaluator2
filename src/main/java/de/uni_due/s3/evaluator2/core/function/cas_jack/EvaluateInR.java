package de.uni_due.s3.evaluator2.core.function.cas_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.r.R;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class EvaluateInR extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws CasEvaluationException, CasNotAvailableException, OpenMathException, EvaluatorException {
		return R.evaluateInCAS(getSageSyntax(arguments.get(0)));
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
