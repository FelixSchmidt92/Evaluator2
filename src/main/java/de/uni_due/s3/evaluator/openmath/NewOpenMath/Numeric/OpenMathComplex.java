package de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric.SimpleNumeric.OpenMathSimpleNumeric;

public class OpenMathComplex extends OpenMathNumeric<OpenMathSimpleNumeric<?>[]>{

	public OpenMathComplex(OpenMathSimpleNumeric<?> arg1, OpenMathSimpleNumeric<?> arg2) {
		this.value = new OpenMathSimpleNumeric<?>[] {arg1, arg2};
	}
	
	
	@Override
	protected String getPartialXML() {
		// TODO 
		return null;
	}


	@Override
	public int getNodesCount() {
		//TODO
		return 0;
	}
	

}
