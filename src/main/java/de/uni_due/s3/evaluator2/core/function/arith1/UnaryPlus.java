package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openMath unary_minus for numbers Example: -3, -4.56
 * 
 * @author frichtscheid, spobel
 *
 */
public class UnaryPlus extends BinaryFunction {

	/**
	 * Expects one argument of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws OpenMathException
	 * @throws EvaluatorException
	 */
	@Override
	protected Object execute(List<Object> arguments) throws OpenMathException, EvaluatorException {
		return arguments.get(0);
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

	@Override
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException {
		return getIntegerSyntax(arguments.get(0));
	}

	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException {
		return getDoubleSyntax(arguments.get(0));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		return getLatexSyntax(arguments.get(0));
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> arguments) throws EvaluatorException {
		return getListSyntax(arguments.get(0));
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException {
		return getRSyntax(arguments.get(0));
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return getSageSyntax(arguments.get(0));
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return getStringSyntax(arguments.get(0));
	}
}
