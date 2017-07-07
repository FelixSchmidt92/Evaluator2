package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

/**
 * Implements plus operation for numbers
 * Example: 3.56 + 4 = 7.56
 * @author frichtscheid
 *
 */
public class Plus extends Function {
	
	/**
	 * @throws OpenMathException 
	 * Expects two arguments of type OMI or OMF
	 * @throws NoRepresentationAvailableException 
	 * @throws CasNotAvailableException 
	 * @throws FunctionInvalidNumberOfArgumentsException 
	 * @throws CasEvaluationException 
	 * @throws FunctionInvalidArgumentTypeException 
	 * @throws  
	 */
	@Override
	protected Object execute(List<Object> arguments) throws CasEvaluationException, FunctionInvalidNumberOfArgumentsException, CasNotAvailableException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException, OpenMathException {
		
		//check if the type of arguments is correct
		if(! (OMTypeChecker.isOMFOrOMI(arguments.get(0)) && OMTypeChecker.isOMFOrOMI(arguments.get(1))) )
			throw new FunctionInvalidArgumentTypeException(this,"integer, float, double");

		//evaluate this method in sage
		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));
		System.out.println(result);
		if(! OMTypeChecker.isOMFOrOMI(result)){
			throw new CasEvaluationException("the result was not of type integer or double");
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
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException{
		return getSageSyntax(arguments.get(0)) + " + " + getSageSyntax(arguments.get(1));
	}

}
