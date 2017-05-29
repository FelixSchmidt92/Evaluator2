package de.uni_due.s3.evaluator.openmathNewOpenMath.Numeric;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric.OpenMathNumeric;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric.OpenMathVector;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric.SimpleNumeric.OpenMathInteger;

public class TestOpenMathVector {
	
	@Test
	public void test(){
		OpenMathNumeric<?>[] ele = {
		new OpenMathInteger(4),
		new OpenMathInteger(3),
		new OpenMathInteger(-2)};
		
		OpenMathVector t = new OpenMathVector(ele);

		System.out.println(t.getXML());
	}

}
