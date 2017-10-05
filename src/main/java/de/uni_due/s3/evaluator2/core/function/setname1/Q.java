package de.uni_due.s3.evaluator2.core.function.setname1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements Rational Field
 * 
 * @author spobel
 *
 */
public class Q extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMSymbol.SETNAME1_Q;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> omel) throws EvaluatorException {
		return "\\mathbb{Q}";
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "QQ";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return "Q";
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
