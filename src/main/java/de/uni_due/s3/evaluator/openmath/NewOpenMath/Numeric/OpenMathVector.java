package de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathTerminal;

public class OpenMathVector extends OpenMathNumeric<OpenMathNumeric<?>[]>{

	public OpenMathVector(OpenMathNumeric<?>[] values){
		this.value = values;
	}
	
	
	@Override
	protected String getPartialXML() {
		String xml = "";
		for(OpenMathTerminal<?> omt : value){
			//xml += omt.getPartialXML(); //TODO
		}
		return "<OMA><OMS cd=\"set1\" name=\"set\"/>" + xml + "</OMA>";
	}


	@Override
	public int getNodesCount() {
		int count = 1;
		for(OpenMathTerminal<?> omt : value){
			count += omt.getNodesCount();
		}
		return count;
	}
}
