package de.uni_due.s3.evaluator2.core.function.nums1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openmath true
 * 
 * @author spobel
 *
 */
public class Infinity extends ConstructorFunction {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMSymbol.NUMS1_INFINITY;
	}
	
	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return Double.POSITIVE_INFINITY;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> omel) throws EvaluatorException {
		return "\\\\infty";
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "Infinity";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "Infinity";
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
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		List<Object> omList = new ArrayList<>();
		omList.add(OMSymbol.NUMS1_INFINITY);
		return omList;
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return "Inf";
	}

}
