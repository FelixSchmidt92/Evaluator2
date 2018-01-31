package de.uni_due.s3.evaluator2.function.eval_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Function does normal evaluation. This Function has to be deprecated.
 * 
 * @author spobel
 *
 */
public class Eval extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMToResultVisitor.getInstance().visit(arguments.get(0));
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
