package de.uni_due.s3.evaluator.core.function.functions.arith1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMI;

/**
 * Implements a modulus operation.
 * Example: 3%2 = 1
 * @author frichtscheid
 *
 */
public class Modulus extends Function {

	/**
	 * Expects two argument of Type OMI
	 * @return OMI
	 * @throws FunctionInvalidArgumentTypeException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try {
			OMI left = (OMI)arguments.get(0);
			OMI right = (OMI)arguments.get(1);
		
			Double leftValue = NumberUtils.convertOMIOMFToDouble(left);
			Double rightValue = NumberUtils.convertOMIOMFToDouble(right);
			return NumberUtils.convertDoubleToOMIOMF(leftValue - rightValue);
		} catch (InputMismatchException e) {
			throw new FunctionInvalidArgumentTypeException(this,"integer");
		}
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getPartialSage(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException{
		return getSageSyntax(arguments.get(0)) + " % " + getSageSyntax(arguments.get(1));
	}

}
