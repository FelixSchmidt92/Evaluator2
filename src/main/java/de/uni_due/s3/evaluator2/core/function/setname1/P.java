package de.uni_due.s3.evaluator2.core.function.setname1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements Complex Field
 * 
 * @author spobel
 *
 */
public class P extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMSymbol.SETNAME1_P;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> omel) throws EvaluatorException {
		return "\\mathbb{P}";
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "PP";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return "P";
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
