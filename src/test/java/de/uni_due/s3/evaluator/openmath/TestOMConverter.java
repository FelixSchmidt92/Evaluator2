package de.uni_due.s3.evaluator.openmath;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMOBJ;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMConverter;

public class TestOMConverter {

	@Test
	public void testStringToOMParsing() throws JAXBException{
		String xmlOpenMath = "<OMOBJ></OMOBJ>";
		assertTrue( (OMConverter.<OMOBJ>toObject(xmlOpenMath)) instanceof OMOBJ);
		
		xmlOpenMath = "<OMOBJ><OMA><OMI>6</OMI></OMA></OMOBJ>";
		OMOBJ omobj = OMConverter.<OMOBJ>toObject(xmlOpenMath);
		assertTrue(omobj.getOMA()!= null);
		
		xmlOpenMath = "<OMI>5</OMI>";
		OMI omi = OMConverter.<OMI>toObject(xmlOpenMath);
		assertTrue(omi.getValue().equals("5"));
	}
	
	@Test
	public void testOMtoStringParsing() throws JAXBException{
		String xmlOpenMath = "<OMA><OMI>6</OMI></OMA>";
		OMA omobj = OMConverter.<OMA>toObject(xmlOpenMath);
	
		System.out.println(OMConverter.toString(omobj));
		assertTrue(xmlOpenMath.equals(OMConverter.toString(omobj)));
	}
}
