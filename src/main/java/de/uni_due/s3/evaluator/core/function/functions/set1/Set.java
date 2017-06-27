package de.uni_due.s3.evaluator.core.function.functions.set1;

import java.util.List;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMS;

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
	

}
