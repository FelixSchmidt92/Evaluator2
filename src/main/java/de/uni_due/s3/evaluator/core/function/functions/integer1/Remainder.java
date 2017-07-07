package de.uni_due.s3.evaluator.core.function.functions.integer1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;


/**
 * Implements a modulus operation.
 * Example: 3%5 = 3
 * @author frichtscheid
 *
 */
public class Remainder extends Function{
	
	/**
	 * Expects two argument of type OMI
	 * @return OMI
	 * @throws FunctionInvalidArgumentTypeException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		if(! (OMTypeChecker.isOMI(arguments.get(0)) && OMTypeChecker.isOMI(arguments.get(1))))
			throw new FunctionInvalidArgumentTypeException(this,"integer");
		
		try {
			OMI left = (OMI)arguments.get(0);
			OMI right = (OMI)arguments.get(1);
		
			Double leftValue = NumberUtils.convertOMIOMFToDouble(left);
			Double rightValue = NumberUtils.convertOMIOMFToDouble(right);
			return NumberUtils.convertDoubleToOMIOMF(leftValue % rightValue);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this,"integer");
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
		return getSageSyntax(arguments.get(0)) + " % " + getSageSyntax(arguments.get(1));
	}


}
