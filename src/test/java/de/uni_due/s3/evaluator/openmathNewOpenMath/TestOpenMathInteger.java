package de.uni_due.s3.evaluator.openmathNewOpenMath;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathInteger;

public class TestOpenMathInteger {

	@Test
	public void testGetValue(){
		OpenMathInteger omi = new OpenMathInteger(5);
		assertTrue(omi.getValue() == 5);
	}
	
	@Test
	public void testGetXML(){
		OpenMathInteger omi = new OpenMathInteger(5);
		assertTrue(omi.getXML().equals("<OMOBJ><OMI>5</OMI></OMOBJ>"));
	}
	
	@Test
	public void testNodesCount(){
		OpenMathInteger omi = new OpenMathInteger(1);
		assertTrue(omi.getNodesCount() == 1);
	}
	
	@Test
	public void testIsTerminal(){
		OpenMathInteger omi = new OpenMathInteger(2);
		assertTrue(omi.isTerminal());
	}
}
