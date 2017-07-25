package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator.core.PolyUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Implements the derivation of a given polynomial.
 * Example derive(1+a^2) = 2*a
 * @author frichtscheid
 *
 */
public class Derive extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws InvalidResultTypeException, CasEvaluationException, FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		//check if args have the correct type
		if(!OMTypeChecker.isOMSTR(arguments.get(0)) || !OMTypeChecker.isOMSTR(arguments.get(1)))
			throw new FunctionInvalidArgumentTypeException(this,"String");

		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		if (!OMTypeChecker.isOMSTR(result)) {
			throw new InvalidResultTypeException(this, "String");
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
		return init+function+" f.derivative("+((OMSTR)arguments.get(1)).getContent()+");";
	}

}
