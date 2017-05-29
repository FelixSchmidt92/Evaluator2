package de.uni_due.s3.evaluator.openmathNewOpenMath.Numeric.SimpleNumeric;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric.SimpleNumeric.OpenMathVariable;

public class TestOpenMathVariable {

	@Test
	public void testGetValue(){
		OpenMathVariable omi = new OpenMathVariable("x");
		assertTrue(omi.getValue().equals("x"));
	}
	
	@Test
	public void testGetXML(){
		OpenMathVariable omi = new OpenMathVariable("x");
		assertTrue(omi.getXML().equals("<OMOBJ><OMV name=\"x\"/></OMOBJ>"));
	}
	
	@Test
	public void testNodesCount(){
		OpenMathVariable omi = new OpenMathVariable("x");
		assertTrue(omi.getNodesCount() == 1);
	}
	
	@Test
	public void testIsTerminal(){
		OpenMathVariable omi = new OpenMathVariable("x");
		assertTrue(omi.isTerminal());
	}
}
