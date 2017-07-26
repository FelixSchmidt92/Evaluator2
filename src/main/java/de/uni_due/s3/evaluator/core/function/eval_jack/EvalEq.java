package de.uni_due.s3.evaluator.core.function.eval_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator.core.PolyUtils;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Subtracts two given expression and return the result as stated in <a href="https://jack-community.org/wiki/index.php/EvalEq">EvalEq</a>
 * Example : evalEq('x^2-1','x') => 'x^2-x-1
 * @author frichtscheid
 *
 */
public class EvalEq extends Function {

	/**
	 * The arguments should be a OMA which shouldn't be evaluated. Otherwise we can't have a polynomial
	 */
	@Override
	public boolean argumentsShouldBeEvaluated() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Expects two arguments, both of them of type OMA
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
	CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {

		//check if args have the correct type
		if(!OMTypeChecker.isOMA(arguments.get(0)) || !OMTypeChecker.isOMA(arguments.get(1)))
			throw new FunctionInvalidArgumentTypeException(this,"(0) polynomial, (1) polynomial");

		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		
		if (!OMTypeChecker.isOMA(result)) {
			throw new InvalidResultTypeException(this, "polynomial");
		}
		return result;

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
	 * The result should be like this:
	 * R.'<' Variablen '>'=RR[]; f= polynomial1; g= polynomial2; f-g;
	 */
	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		if(!OMTypeChecker.isOMA(arguments.get(0)) || !OMTypeChecker.isOMA(arguments.get(1)))
			throw new FunctionInvalidArgumentTypeException(this,"(0) polynomial, (1) polynomial");

		// get all variables from the polynomials and build the sageSyntax
		Set<String> variables1 = PolyUtils.getVariables(getSageSyntax(arguments.get(0)));
		Set<String> variables2 = PolyUtils.getVariables(getSageSyntax(arguments.get(1)));
		variables1.addAll(variables2);
		String init = "R.<";
		for(String v :variables1){
			init+=v;
			init+=",";
		}
		init = init.substring(0, init.length()-1);
		init +=">=RR[]; ";
		String f = "f="+getSageSyntax(arguments.get(0))+"; ";
		String g = "g="+getSageSyntax(arguments.get(1))+"; ";
		
		return init+f+g+"f-g";
	}
}
