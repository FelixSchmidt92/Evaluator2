package de.uni_due.s3.evaluator2.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openmath true
 * 
 * @author spobel
 *
 */
public class True extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMSymbol.LOGIC1_TRUE;
	}
	
	@Override
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return true;
	}
	
	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return 1.0;
	}
	
	@Override
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return 1;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> omel) throws EvaluatorException {
		return "True";
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "True";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "True";
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "TRUE";
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
