package de.uni_due.s3.evaluator2.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openMath unary plus for numbers Example:  ++3   +++++1     +1    +-1 
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
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Double value = getDoubleSyntax(arguments.get(0));
		return OMCreator.createOMIOMF(value);
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
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getIntegerSyntax(arguments.get(0));
	}

	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getDoubleSyntax(arguments.get(0));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getLatexSyntax(arguments.get(0));
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getListSyntax(arguments.get(0));
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getRSyntax(arguments.get(0));
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getSageSyntax(arguments.get(0));
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getStringSyntax(arguments.get(0));
	}
}
