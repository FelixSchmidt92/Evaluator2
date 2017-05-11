package de.uni_due.s3.evaluator.openmath;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestOpenMathInteger {

	@Test
	public void testIsInteger() {
		assertTrue(OpenMathObjectCreator.createOpenMathInteger(1).isInteger());
		assertTrue(OpenMathObjectCreator.createOpenMathInteger(-1).isInteger());
		assertTrue(OpenMathObjectCreator.createOpenMathApplication(OpenMathSymbols.ARITH1_MINUS, OpenMathObjectCreator.createOpenMathInteger(1)).isInteger());
		assertFalse(OpenMathObjectCreator.createOpenMathString("Test").isInteger());
	}
}
