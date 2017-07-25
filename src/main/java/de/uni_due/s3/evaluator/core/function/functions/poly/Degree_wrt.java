package de.uni_due.s3.evaluator.core.function.functions.poly;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.PolyUtils;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Implements the polynomial degree-function with two arguments as specified in openmath poly-cd.
 * Example: deg(a^3+b^2,a) = 3
 * See <a href="www.openmath.org/cd/poly.xhtml#degree_wrt">openmath</a>
 * 
 * @author frichtscheid
 *
 */
public class Degree_wrt extends Function{

	/**
	 * Expects two arguments of type string.
	 * The first one is the polynomial, the second one the dependend variable
	 * If the second argument will be an empty string, the degree function returns the highest exponent
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		
		//check if args have the correct type
		if(!OMTypeChecker.isOMSTR(arguments.get(0)) || !OMTypeChecker.isOMSTR(arguments.get(1)))
			throw new FunctionInvalidArgumentTypeException(this,"String");

		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		if (!OMTypeChecker.isOMI(result)) {
			throw new InvalidResultTypeException(this, "integer");
		}
		return result;

	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		if(!OMTypeChecker.isOMSTR(arguments.get(0)) || !OMTypeChecker.isOMSTR(arguments.get(1)))
			throw new FunctionInvalidArgumentTypeException(this,"String");
		
		// get all variables from the polynomial and build the sageSyntax
		Set<String> variables = PolyUtils.getVariables(((OMSTR) arguments.get(0)).getContent());
		String init = "R.<";
		for(String v :variables){
			init+=v;
			init+=",";
		}
		init = init.substring(0, init.length()-1);
		init +=">=RR[]; ";
		String function = "f="+((OMSTR)arguments.get(0)).getContent()+";";
		return init+function+" f.degree("+((OMSTR)arguments.get(1)).getContent()+");";
	}

}
