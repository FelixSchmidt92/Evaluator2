package de.uni_due.s3.evaluator2.core.function.complex1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class Complex_Cartesian extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.COMPLEX1_CARTESIAN_COMPLEX, arguments);
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String real = getLatexSyntax(arguments.get(0));
		String imag = getLatexSyntax(arguments.get(1));
		return real + " + \\mathrm{i} * " + imag;
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String real = getSageSyntax(arguments.get(0));
		String imag = getSageSyntax(arguments.get(1));
		return real + " + I * " + imag;
	}
	
	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		List<Object> omList = new ArrayList<>();
		omList.add(OMCreator.createOMA(OMSymbol.COMPLEX1_CARTESIAN_COMPLEX, omel));
		return omList;
	}

}
