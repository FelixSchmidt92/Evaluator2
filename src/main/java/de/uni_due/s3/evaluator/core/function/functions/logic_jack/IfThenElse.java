package de.uni_due.s3.evaluator.core.function.functions.logic_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;

/**
 * Implements the jack ifthenelse-operation{@link  https://jack-community.org/wiki/index.php/Ifthenelse}.
 *  
 */
public class IfThenElse extends Function {

	/**
	 * Expects 3 arguments:
	 * 	1.) the condition - something that can be reduced to a boolean value
	 *  2.) value if true - the value which should be returned if the condition is true
	 *  3.) value if false - the value which should be returned if the condition is false
	 *  
	 *  @return second or third argument
	 * @throws FunctionInvalidArgumentTypeException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws FunctionInvalidArgumentTypeException {
		OMS condition;
		Object firstArg = arguments.get(0);
		Object trueValue = arguments.get(1);
		Object falseValue = arguments.get(2);
		
		//convert the condition to boolean value
		if(OMTypeChecker.isOMS(firstArg) && ( ((OMS)firstArg).equals(OMSymbol.LOGIC1_TRUE) || ((OMS)firstArg).equals(OMSymbol.LOGIC1_FALSE) )){
			condition = (OMS) firstArg;
		}else if( OMTypeChecker.isOMF(firstArg)){
			condition = ( ((OMF)firstArg).getDec()>0)? OMSymbol.LOGIC1_TRUE:OMSymbol.LOGIC1_FALSE; 
		}else if( OMTypeChecker.isOMI(firstArg)){
			condition = ( Integer.parseInt(((OMI)firstArg).getValue())>0)? OMSymbol.LOGIC1_TRUE:OMSymbol.LOGIC1_FALSE;
		}else{
			throw new FunctionInvalidArgumentTypeException(this, "1.) boolean, 2.) any, 3.) any");
		}
		
		if(condition.equals(OMSymbol.LOGIC1_TRUE))
			return trueValue;
		else
			return falseValue;
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
}
