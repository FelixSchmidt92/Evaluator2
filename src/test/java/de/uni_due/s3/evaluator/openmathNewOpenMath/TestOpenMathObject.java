package de.uni_due.s3.evaluator.openmathNewOpenMath;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathObject;

public class TestOpenMathObject {
	
	// Abstract Implementation
	OpenMathObject<?> omo = new OpenMathObject<OpenMathObject<?>>() {

		@Override
		public String getPartialXML() {
			return "this";
		}
	};
	
	@Test
	public void testGetValue(){
		assertTrue(omo.getValue() == null);
	}
	
	@Test
	public void testGetXML(){
		assertTrue(omo.getXML().equals("<OMOBJ>this</OMOBJ>"));
	}
	
	@Test
	public void testNodesCount(){
		assertTrue(omo.getNodesCount() == 1);
	}
	
	@Test
	public void testisTerminal(){
		assertTrue(omo.isTerminal());
	}
}
