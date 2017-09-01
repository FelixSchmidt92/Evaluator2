package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.OMUtils;
import de.uni_due.s3.evaluator2.core.PolyUtils;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMBIND;
import de.uni_due.s3.openmath.jaxb.OMBVAR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class Product extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// checking arguments
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.INTERVAL1_INTERVAL))
			throw new FunctionInvalidArgumentTypeException(this, "first argument has to be an intervall");
		if (!(arguments.get(1) instanceof OMBIND))
			throw new FunctionInvalidArgumentTypeException(this, "there is no variable bound to the product");

		return Sage.evaluateInCAS(getPartialSageSyntax(arguments));

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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		OMA intervall = (OMA) arguments.get(0);
		String lowerBound, upperBound;
		
		lowerBound = getSageSyntax(intervall.getOmel().get(1));
		upperBound = getSageSyntax(intervall.getOmel().get(2));
		
		OMBIND ombind = (OMBIND) arguments.get(1);
		OMBVAR ombvar = (OMBVAR) ombind.getContent().get(1);
		OMV variable = (OMV) ombvar.getOmvar().get(0); // use just one variable
		OMA polynomial = (OMA) ombind.getContent().get(2);
		String term = getSageSyntax(polynomial);
		
		String sageVar = PolyUtils.getSageSyntaxVariableRepresentation(term);
		
		String result =  sageVar+" var('"+getSageSyntax(variable)+"'); prod("+term+" for "+getSageSyntax(variable)+" in ("+lowerBound+".."+upperBound+"))";
		System.out.println(result);
		return result;
	
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		
		OMA intervall = (OMA) arguments.get(0);
		String lowerBound, upperBound;
		
		try {
			lowerBound = OMUtils.convertOMToString(intervall.getOmel().get(1));
			upperBound = OMUtils.convertOMToString(intervall.getOmel().get(2));
		} catch (InputMismatchException ime) {
			throw new FunctionInvalidArgumentException(this, "intervall could not be converted to a number");
		}
		OMBIND ombind = (OMBIND) arguments.get(1);
		OMBVAR ombvar = (OMBVAR) ombind.getContent().get(1);
		OMV omv = (OMV) ombvar.getOmvar().get(0); // use just one variable
		OMA oma = (OMA) ombind.getContent().get(2);

		return "\\prod_{"+lowerBound+"}^{"+upperBound+"} {" + getLatexSyntax(oma) + "}";
	}
	
	/**
	 * Should be false so that the interval wont be evaluated
	 */
	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
