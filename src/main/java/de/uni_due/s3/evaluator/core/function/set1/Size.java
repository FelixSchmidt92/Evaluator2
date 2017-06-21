package de.uni_due.s3.evaluator.core.function.set1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMI;

public class Size extends Function {
	

	@Override
	protected Object execute(List<Object> arguments) {
		
		//FIXME Überprüfen ob es ein set ist
		OMA set = (OMA)arguments.get(0);
		
		OMI omi = new OMI();
		omi.setValue( Integer.toString(set.getOmel().size()-1) ); //wegen oms
		return omi;

	
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

}
