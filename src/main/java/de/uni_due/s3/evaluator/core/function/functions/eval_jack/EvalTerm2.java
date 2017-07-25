package de.uni_due.s3.evaluator.core.function.functions.eval_jack;

import java.util.List;
import java.util.Set;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.core.function.PolyUtils;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Inserts values for two different variables in a term and calculates the result.
 * @author frichtscheid
 *
 */
public class EvalTerm2 extends Function {

	/**
	 * Expects three arguments: (1) polynomial of type String, (2) and (3): value of type String or integer
	 * @return OMI or OMF
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
	CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {

	
		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		
		if (!OMTypeChecker.isOMFOrOMI(result)) {
			throw new InvalidResultTypeException(this, "integer, float, double");
		}
		return result;

	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		//check if args have the correct type
		if(!OMTypeChecker.isOMSTR(arguments.get(0)) || !(OMTypeChecker.isOMSTR(arguments.get(1))||OMTypeChecker.isOMFOrOMI(arguments.get(1)) )
				|| !(OMTypeChecker.isOMSTR(arguments.get(2)) || OMTypeChecker.isOMFOrOMI(arguments.get(2))))
			throw new FunctionInvalidArgumentTypeException(this,"String");

		try{
		
			String polynomial = ((OMSTR) arguments.get(0)).getContent();
			String var1 = NumberUtils.convertOMToString(arguments.get(1));
			String var2 = NumberUtils.convertOMToString(arguments.get(2));
			
			// get all variables from the polynomial and build the sageSyntax
			Set<String> variables1 = PolyUtils.getVariables(polynomial);
			String init = "R.<";
			for(String v :variables1){
				init+=v;
				init+=",";
			}
			init = init.substring(0, init.length()-1);
			init +=">=RR[]; ";
			String f = "f="+((OMSTR)arguments.get(0)).getContent()+"; ";
			return init+f+"f("+var1+","+var2+");";
		}catch(InputMismatchException ie){
			throw new FunctionInvalidArgumentTypeException(this,"String");
		}
	}
}
