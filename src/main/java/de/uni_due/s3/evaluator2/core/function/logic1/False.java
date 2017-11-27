package de.uni_due.s3.evaluator2.core.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openmath false
 * 
 * @author spobel
 *
 */
public class False extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMSymbol.LOGIC1_FALSE;
	}
	
	@Override
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return false;
	}
	
	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return 0.0;
	}
	
	@Override
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return 0;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> omel) throws EvaluatorException {
		return "False";
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "False";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "False";
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "FALSE";
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
