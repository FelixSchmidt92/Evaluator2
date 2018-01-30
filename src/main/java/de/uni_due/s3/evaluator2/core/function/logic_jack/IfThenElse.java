package de.uni_due.s3.evaluator2.core.function.logic_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the jack
 * ifthenelse-operation{@link https://jack-community.org/wiki/index.php/Ifthenelse}.
 * 
 */
public class IfThenElse extends Function {

	/**
	 * Expects 3 arguments: 1.) the condition - something that can be reduced to a
	 * boolean value 2.) value if true - the value which should be returned if the
	 * condition is true 3.) value if false - the value which should be returned if
	 * the condition is false
	 * 
	 * @return second or third argument
	 * @throws EvaluatorException 
	 * @throws FunctionInvalidArgumentTypeException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		boolean condition = getBooleanSyntax(OMToResultVisitor.getInstance().visit(arguments.get(0)));

		if (condition) {
			return OMToResultVisitor.getInstance().visit(arguments.get(1));
		} else {
			return OMToResultVisitor.getInstance().visit(arguments.get(2));
		}
	}

	@Override
	protected int minArgs() {
		return 3;
	}

	@Override
	protected int maxArgs() {
		return 3;
	}
	
	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
