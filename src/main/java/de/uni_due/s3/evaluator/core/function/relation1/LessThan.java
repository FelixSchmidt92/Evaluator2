package de.uni_due.s3.evaluator.core.function.relation1;

import java.util.List;

import de.uni_due.s3.evaluator.core.OMUtils;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

/**
 * Implements openmath arithmetic relation lt.
 * Example: 
 * @author frichtscheid
 *
 */
public class LessThan extends Function {

	/**
	 * Tests if the first argument is less than the second argument.
	 * Expects 2 arguments of type OMI or OMF
	 * 
	 * @throws FunctionInvalidArgumentTypeException 
	 * 
	 * return true or false as OMS
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		try{
			double first = OMUtils.convertOMIOMFToDouble(arguments.get(0));
			double second = OMUtils.convertOMIOMFToDouble(arguments.get(1));
			return (first<second)? OMSymbol.LOGIC1_TRUE:OMSymbol.LOGIC1_FALSE ;
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
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		// TODO Auto-generated method stub
		return getSageSyntax(arguments.get(0))+" < "+getSageSyntax(arguments.get(1));
	}

}