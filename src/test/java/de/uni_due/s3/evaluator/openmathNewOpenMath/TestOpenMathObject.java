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
	
	// Abstract Implementation
	OpenMathObject<?> omo2 = new OpenMathObject<OpenMathObject<?>>() {

		@Override
		public String getPartialXML() {
			return "something else";
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
	public void testIsTerminal(){
		assertTrue(omo.isTerminal());
	}
	
	@Test
	public void testEquals(){
		assertTrue(!omo.equals(null));
		assertTrue(omo.equals(omo));
		assertTrue(!omo.equals(omo2));
		assertTrue(!omo2.equals(omo));
	}
}
