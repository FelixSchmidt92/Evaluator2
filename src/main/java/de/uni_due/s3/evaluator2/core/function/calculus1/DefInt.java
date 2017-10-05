package de.uni_due.s3.evaluator2.core.function.calculus1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMBIND;
import de.uni_due.s3.openmath.jaxb.OMBVAR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the integral function from calculus1, defint. This integral has a
 * lower and upper bound.
 * 
 * @author frichtscheid
 *
 */
public class DefInt extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// checking arguments
		if (!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.INTERVAL1_INTEGER_INTERVAL))
			throw new FunctionInvalidArgumentTypeException(this, "first argument has to be an intervall");
		if (!(arguments.get(1) instanceof OMBIND))
			throw new FunctionInvalidArgumentTypeException(this, "there is no variable bound to the polynomial");

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

	/**
	 * creates a sage query for an definite integral. See <a href=
	 * "doc.sagemath.org/html/en/reference/calculus/sage/symbolic/integration/integral.html">reference</a>
	 * 
	 */
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

		String query = "from sage.symbolic.integration.integral import definite_integral;";
		query += " var(\'" + getSageSyntax(variable) + "\');";
		query += " definite_integral(" + getSageSyntax(polynomial) + "," + getSageSyntax(variable) + "," + lowerBound
				+ "," + upperBound + ")";

		return query;
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws EvaluatorException, FunctionException, NoRepresentationAvailableException {
		OMA intervall = (OMA) arguments.get(0);

		String lowerBound = getStringSyntax(intervall.getOmel().get(1));
		String upperBound = getStringSyntax(intervall.getOmel().get(2));

		OMBIND ombind = (OMBIND) arguments.get(1);
		OMBVAR ombvar = (OMBVAR) ombind.getContent().get(1);
		OMV omv = (OMV) ombvar.getOmvar().get(0); // use just one variable
		OMA oma = (OMA) ombind.getContent().get(2);

		return "\\int_{" + lowerBound + "}^{" + upperBound + "} {" + getLatexSyntax(oma) + "} d" + getLatexSyntax(omv);

	}

	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}
}
