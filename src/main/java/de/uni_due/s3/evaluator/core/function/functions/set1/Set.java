package de.uni_due.s3.evaluator.core.function.functions.set1;

import java.util.List;


import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;

public class Set extends Function {

	@Override
	protected Object execute(List<Object> arguments) {
		System.out.println("Set");
		OMA oma = new OMA();
		OMS oms = new OMS();
		
		oms.setCd("set1");
		oms.setName("set");
		
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
