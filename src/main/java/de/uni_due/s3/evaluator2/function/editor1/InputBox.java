package de.uni_due.s3.evaluator2.function.editor1;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This is a "pseudo"-Terminal
 * 
 * In The Editor in Jack2, an empty Input can send to the Evaluator as an OMOBJ.
 * This is a useless Terminal, which cannot be executed! Executing results in a
 * FunctionException.
 * 
 * However a Rep. is needed for this "pseudo"-Terminal, because Jack2 tries to
 * represent this at the SolutionList. (Jack2 also tries to execute this)  
 * 
 * @author dlux
 *
 */
public class InputBox extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
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
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "";
	}
}
