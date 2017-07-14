package de.uni_due.s3.evaluator.core.function.functions.set1;

import java.util.List;


import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;

/**
 * Implements a list. Values can occur more than ones in this list. In a normal set values could only occur ones!.
 * We have to implement it this way, otherwise we wouldn't be compatible with evaluator1
 *  
 * @author frichtscheid
 *
 */
public class Set extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		OMA oma = new OMA();
		OMS oms = OMSymbol.SET1_SET;
		
		List<Object> omel = oma.getOmel();
		omel.add(oms);
		omel.addAll(arguments);
		
		return oma;
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException{
		String set = "{";
			for(Object arg : arguments){
				set += getSageSyntax(arg) + ", ";
			}
			set = set.substring(0, set.length() - 2); // Removing ", "
		
		return set + "}";
	}

}
