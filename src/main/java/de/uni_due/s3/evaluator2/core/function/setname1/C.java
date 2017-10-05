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
public class C extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMSymbol.SETNAME1_C;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> omel) throws EvaluatorException {
		return "\\mathbb{C}";
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "CC";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return "C";
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
