package de.uni_due.s3.evaluator.openmathNewOpenMath;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathFloat;


public class TestOpenMathFloat {

	@Test
	public void testGetValue(){
		OpenMathFloat omi = new OpenMathFloat(3.1234);
		assertTrue(omi.getValue() == 3.1234);
	}
	
	@Test
	public void testGetXML(){
		OpenMathFloat omi = new OpenMathFloat(3.1234);
		assertTrue(omi.getXML().equals("<OMOBJ><OMF dec=\"3.1234\"/></OMOBJ>"));
	}
	
	@Test
	public void testNodesCount(){
		OpenMathFloat omi = new OpenMathFloat(3.1234);
		assertTrue(omi.getNodesCount() == 1);
	}
	
	@Test
	public void testIsTerminal(){
		OpenMathFloat omi = new OpenMathFloat(3.1234);
		assertTrue(omi.isTerminal());
	}
}
