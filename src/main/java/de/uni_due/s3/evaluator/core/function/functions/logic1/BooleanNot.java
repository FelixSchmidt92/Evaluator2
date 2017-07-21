package de.uni_due.s3.evaluator.core.function.functions.logic1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Implement openmath logic not.
 * Example: !true => false
 * 
 * @author frichtscheid
 *
 */
public class BooleanNot extends Function{
	
	/**
	 * Takes one boolean argument and returns the opposite value.
	 * Expects one argument of type OMSymbol.LOGIC_TRUE or LOGIC_FALSE
	 * 
	 * @return true or false as OMS
	 * @throws FunctionInvalidArgumentTypeException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		OMS arg1;
		String omsCd = OMSymbol.LOGIC1_FALSE.getCd();
		String omsTrue = OMSymbol.LOGIC1_TRUE.getName();
		String omsFalse = OMSymbol.LOGIC1_FALSE.getName();
		
		//type check if argument are of type oms
		if(OMTypeChecker.isOMS(arguments.get(0))){
			arg1 = (OMS) arguments.get(0);

			
			//syntax check if argument are logic booleans
			if(arg1.getCd().equals(omsCd)){
				if( arg1.getName().equals(omsTrue) || arg1.getName().equals(omsFalse)){
					
					//semantic check if argument is true
					if(arg1.equals(OMSymbol.LOGIC1_TRUE))
						return OMSymbol.LOGIC1_FALSE;
					else
						return OMSymbol.LOGIC1_TRUE;
				}
			}
		}		
		
		throw new FunctionInvalidArgumentTypeException(this,"boolean");
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {

		return "not("+getSageSyntax(arguments.get(0))+")";
	}
}
