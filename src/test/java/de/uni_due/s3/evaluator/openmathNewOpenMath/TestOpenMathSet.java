package de.uni_due.s3.evaluator.openmathNewOpenMath;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathSet;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathTerminal;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric.SimpleNumeric.OpenMathFloat;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.Numeric.SimpleNumeric.OpenMathInteger;
import de.uni_due.s3.evaluator.openmath.NewOpenMath.Text.OpenMathString;

public class TestOpenMathSet {

	private OpenMathTerminal<?>[] listTwo = {new OpenMathInteger(1)};
	private OpenMathTerminal<?>[] listOne = {new OpenMathInteger(5), new OpenMathFloat(3.1), 
			new OpenMathString("abc"), new OpenMathSet(Arrays.asList(listTwo))};
	private List<OpenMathTerminal<?>> elements = Arrays.asList(listOne);

	@Test
	public void testGetValue(){
		OpenMathSet oms = new OpenMathSet(elements);
		assertTrue(oms.getValue().equals(elements));
	}
	
	@Test
	public void testGetXML(){
		OpenMathSet oms = new OpenMathSet(elements);

		assertTrue(oms.getXML().equals("<OMOBJ><OMA>"
				+ "<OMS cd=\"set1\" name=\"set\"/>"
				+ "<OMI>5</OMI><OMF dec=\"3.1\"/><OMSTR>abc</OMSTR>"	//First 3 Set-Values
				+ "<OMA><OMS cd=\"set1\" name=\"set\"/><OMI>1</OMI></OMA>" //4. Set-Value
				+ "</OMA></OMOBJ>"));
	}
	
	@Test
	public void testNodesCount(){
		OpenMathSet oms = new OpenMathSet(elements);
		assertTrue(oms.getNodesCount() == 6);
	}
	
	@Test
	public void testIsTerminal(){
		OpenMathSet oms = new OpenMathSet(elements);
		assertTrue(oms.isTerminal());
	}
}
