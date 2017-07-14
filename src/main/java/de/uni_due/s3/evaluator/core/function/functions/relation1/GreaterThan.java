package de.uni_due.s3.evaluator.core.function.functions.relation1;

/**
 * Implements openmath relation1 gt.
 * Example: 4 > 4 => true;
 */
import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.NumberUtils;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

public class GreaterThan extends Function {

	/**
	 * Tests if the first argument is greater than the second argument.
	 * Expects 2 arguments of type OMI or OMF
	 * 
	 * @throws FunctionInvalidArgumentTypeException 
	 * 
	 * return true or false as OMS
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try{
			double first = NumberUtils.convertOMIOMFToDouble(arguments.get(0));
			double second = NumberUtils.convertOMIOMFToDouble(arguments.get(1));
			return (first>second)? OMSymbol.LOGIC1_TRUE:OMSymbol.LOGIC1_FALSE ;
		}catch(Exception e){
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException {
		// TODO Auto-generated method stub
		return getSageSyntax(arguments.get(0))+" > "+getSageSyntax(arguments.get(1));
	}

}
