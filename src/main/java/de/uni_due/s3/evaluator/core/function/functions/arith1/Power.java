package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the Math.pow function for functions
 * Example: 2^3 = 8 or 
 * Usage: power(2,3) = 8
 * @author frichtscheid
 *
 */
public class Power extends Function{

	/**
	 * Expects two arguments either of type OMI or OMF
	 * @return OMI or OMF
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		Object base = arguments.get(0);
		Object exponent = arguments.get(1);
		if (!(OMTypeChecker.isOMFOrOMI(base) && OMTypeChecker.isOMFOrOMI(exponent)))
			throw new FunctionInvalidArgumentTypeException(this,"integer, float, double");
		Double b;
		Double e;
		try {
			b = NumberUtils.convertOMIOMFToDouble(base);
			e = NumberUtils.convertOMIOMFToDouble(exponent);
			return NumberUtils.convertDoubleToOMIOMF(Math.pow(b,e));
		} catch (InputMismatchException e1) {
			// TODO Auto-generated catch block
			throw new FunctionInvalidArgumentTypeException(this,"integer, float, double");
		}		
		
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
	public String getPartialSageSyntax(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException{
		return "power("+getSageSyntax(arguments.get(0))+","+getSageSyntax(arguments.get(1))+")";
	}

}
