package de.uni_due.s3.evaluator.openmathNewOpenMath;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathApplication;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathObject;

public class TestOpenMathApplication {

	
	@Test
	public void testIsTerminal(){
		LinkedList<OpenMathObject<?>> ll = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llTwo = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llThree = new LinkedList<>();
		
		OpenMathApplication oma = new OpenMathApplication("set1", "set", ll);
		assertTrue(oma.isTerminal());
		
		OpenMathApplication omaTwo = new OpenMathApplication("NoSet", "NotTerminal", llTwo);
		assertTrue(!omaTwo.isTerminal());
		
		oma.addChild(new OpenMathApplication("linalg2", "matrix", llThree));
		assertTrue(oma.isTerminal());
		
		oma.addChild(omaTwo);
		assertTrue(!oma.isTerminal());
	}
	
	@Test
	public void testGetNodeCounts(){
		LinkedList<OpenMathObject<?>> ll = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llTwo = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llThree = new LinkedList<>();
		
		OpenMathApplication oma = new OpenMathApplication("set1", "set", ll);
		assertTrue(oma.getNodesCount() == 1);
		
		OpenMathApplication omaTwo = new OpenMathApplication("NoSet", "NotTerminal", llTwo);
		assertTrue(omaTwo.getNodesCount() == 1);
		
		oma.addChild(new OpenMathApplication("linalg", "NotVector", llThree));
		assertTrue(oma.getNodesCount() == 2);
		
		oma.addChild(omaTwo);
		assertTrue(oma.getNodesCount() == 3);
	}
	
	@Test
	public void testAddChild(){
		LinkedList<OpenMathObject<?>> ll = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llTwo = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llThree = new LinkedList<>();
		
		OpenMathApplication oma = new OpenMathApplication("set", "set1", ll);
		assertTrue(oma.getNodesCount() == 1);
		
		oma.addChild(new OpenMathApplication("", "", llTwo));
		assertTrue(oma.getNodesCount() == 2);
		
		oma.addChild(new OpenMathApplication("abc", "test", llThree));
		assertTrue(oma.getNodesCount() == 3);
	}
	
	@Test
	public void testGetXML(){
		LinkedList<OpenMathObject<?>> ll = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llTwo = new LinkedList<>();
		LinkedList<OpenMathObject<?>> llThree = new LinkedList<>();
		
		OpenMathApplication oma = new OpenMathApplication("set1", "set", ll);
		assertTrue(oma.getXML().equals("<OMOBJ><OMA><OMS cd=\"set1\" name=\"set\"/></OMA></OMOBJ>"));
		
		OpenMathApplication omaTwo = new OpenMathApplication("NoSet", "NotTerminal", llTwo);
		
		oma.addChild(new OpenMathApplication("smthg", "smthg", llThree));
		assertTrue(oma.getXML().equals("<OMOBJ><OMA><OMS cd=\"set1\" name=\"set\"/><OMA>"
									 + "<OMS cd=\"smthg\" name=\"smthg\"/></OMA></OMA></OMOBJ>"));
		
		oma.addChild(omaTwo);
		assertTrue(oma.getXML().equals("<OMOBJ><OMA><OMS cd=\"set1\" name=\"set\"/>"
									 + "<OMA><OMS cd=\"smthg\" name=\"smthg\"/></OMA>"
									 + "<OMA><OMS cd=\"NoSet\" name=\"NotTerminal\"/></OMA></OMA></OMOBJ>"));
	}
	
	
	
	
}
