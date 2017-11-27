package de.uni_due.s3.evaluator2.core.function.calculus1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSFunctionDictionary;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMBIND;
import de.uni_due.s3.openmath.jaxb.OMBVAR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the integral function from calculus1, int.
 * This integral has no limits
 * @author frichtscheid
 *
 */
public class Int extends Function{

	/**
	 * Expects one argument of type OMBIND. 
	 * Within OMBIND there has to be a corresponding OMS, 
	 * a OMBVAR with on variable, and an OMA the polynomial which should be integrated.
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		
		//collect the variable and the polynomial and pass it to the polynomialjack.integrate function
		if(arguments.get(0) instanceof OMBIND) {
			OMBIND ombind = (OMBIND) arguments.get(0);
			OMBVAR ombvar = (OMBVAR) ombind.getContent().get(1);
			OMV omv = (OMV) ombvar.getOmvar().get(0); // use just one variable
			OMA oma = (OMA) ombind.getContent().get(2);
			
			List<Object> args = new ArrayList<Object>(2);
			args.add(oma);
			args.add(omv);
			
			return OMSFunctionDictionary.getInstance().getFunction(OMSymbol.POLYNOMIALJACK_INTEGRATE).evaluate(args);
		}else {
			throw new FunctionInvalidArgumentTypeException(this,"(0) OMBIND");
		}
	}

	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
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
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		OMBIND ombind = (OMBIND) arguments.get(0);
		OMBVAR ombvar = (OMBVAR) ombind.getContent().get(1);
		OMV omv = (OMV) ombvar.getOmvar().get(0); // use just one variable
		OMA oma = (OMA) ombind.getContent().get(2);
		
		return "\\int "+getLatexSyntax(oma)+" d"+getLatexSyntax(omv);
	}

}
