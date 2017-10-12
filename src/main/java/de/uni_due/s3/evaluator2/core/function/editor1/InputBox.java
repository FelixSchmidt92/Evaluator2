package de.uni_due.s3.evaluator2.core.function.editor1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class InputBox extends Function{

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		throw new FunctionException("The Function: " + this.getClass().getSimpleName() + " cannot be executed");
	}

	@Override
	protected int minArgs() {
		return 0;
	}

	@Override
	protected int maxArgs() {
		return 0;
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		return "";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return "";
	}
}
