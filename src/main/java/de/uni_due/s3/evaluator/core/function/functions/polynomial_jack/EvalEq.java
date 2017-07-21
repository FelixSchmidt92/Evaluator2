package de.uni_due.s3.evaluator.core.function.functions.polynomial_jack;

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
 * Subtracts two given expression and return the result
 * Example : evalEq('x^2-1','x') => 'x^2-x-1
 * @author frichtscheid
 *
 */
public class EvalEq extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
	CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {

		//check if args have the correct type
		if(!OMTypeChecker.isOMSTR(arguments.get(0)) || !OMTypeChecker.isOMSTR(arguments.get(1)))
			throw new FunctionInvalidArgumentTypeException(this,"String");

		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		
		if (!OMTypeChecker.isOMSTR(result)) {
			throw new InvalidResultTypeException(this, "string");
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

	/**
	 * The result should be like this:
	 * R.< Variablen >=RR[]; f= polynomial1; g= polynomial2; f-g;
	 */
	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		if(!OMTypeChecker.isOMSTR(arguments.get(0)) || !OMTypeChecker.isOMSTR(arguments.get(1)))
			throw new FunctionInvalidArgumentTypeException(this,"String");

		// get all variables from the polynomials and build the sageSyntax
		Set<String> variables1 = PolyUtils.getVariables(((OMSTR) arguments.get(0)).getContent());
		Set<String> variables2 = PolyUtils.getVariables(((OMSTR) arguments.get(1)).getContent());
		variables1.addAll(variables2);
		String init = "R.<";
		for(String v :variables1){
			init+=v;
			init+=",";
		}
		init = init.substring(0, init.length()-1);
		init +=">=RR[]; ";
		String f = "f="+((OMSTR)arguments.get(0)).getContent()+"; ";
		String g = "g="+((OMSTR)arguments.get(1)).getContent()+"; ";
		
		return init+f+g+"f-g;";
	}
}
