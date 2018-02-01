package de.uni_due.s3.evaluator2.function.variables_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.AbstractFunction;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This is a wrapper function for FillIn Variables (entered by the student).
 * 
 * If an Exception is thrown within an FillIn-Variable, something inside the
 * FillIn-Variable is erroneous.
 * 
 * The Exception is here caught and wrapped into a Student-Input exception which
 * is thrown.
 * 
 * @author dlux
 *
 */
public class FillIn extends AbstractFunction {

	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> getPartialListSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

}
