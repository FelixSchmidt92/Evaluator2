package de.uni_due.s3.evaluator.openmathNewOpenMath;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathString;

public class TestOpenMathString {

	@Test
	public void testGetValue(){
		OpenMathString omi = new OpenMathString("a");
		assertTrue(omi.getValue().equals("a"));
	}
	
//	@Test
//	public void testGetPartialXML(){
//		OpenMathString omi = new OpenMathString("a");
//		assertTrue(omi.getPartialXML().equals("<OMSTR>a</OMSTR>"));
//	}
	
	@Test
	public void testGetXML(){
		OpenMathString omi = new OpenMathString("a");
		assertTrue(omi.getXML().equals("<OMOBJ><OMSTR>a</OMSTR></OMOBJ>"));
	}
	
	@Test
	public void testNodesCount(){
		OpenMathString omi = new OpenMathString("a");
		assertTrue(omi.getNodesCount() == 1);
	}
	
	@Test
	public void testIsTerminal(){
		OpenMathString omi = new OpenMathString("a");
		assertTrue(omi.isTerminal());
	}
}
