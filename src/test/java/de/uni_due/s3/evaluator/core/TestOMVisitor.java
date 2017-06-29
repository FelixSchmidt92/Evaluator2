package de.uni_due.s3.evaluator.core;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.JAXBOpenMath.openmath.OMA;
import de.uni_due.s3.JAXBOpenMath.openmath.OMB;
import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.JAXBOpenMath.openmath.OMV;
import de.uni_due.s3.evaluator.core.function.OMVisitor;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Plus;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;



public class TestOMVisitor {

	private static Unmarshaller unmarshaller;
	private static OMVisitor omv;
	
	@BeforeClass
	public static void init() throws JAXBException{
		omv = new OMVisitor();
		JAXBContext context = JAXBContext
				.newInstance("de.uni_due.s3.openmath");

		unmarshaller = context.createUnmarshaller();
	}
	
	@Test
	public void visitorTestOMOBJ() throws JAXBException{
		Object result;
		OMOBJ omobj;
		//test OMI
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMI>5</OMI></OMOBJ>"));
		result = omv.visit(omobj);
		assertTrue(result instanceof OMI);
		assertEquals( ((OMI)result).getValue(),"5");
		
		//test OMF
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMF dec=\"5.12345\"></OMF></OMOBJ>"));
		result = omv.visit(omobj);
		assertTrue(result instanceof OMF);
		assertTrue( ((OMF)result).getDec().doubleValue() == 5.12345d);
		
		//test OMS
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMS cd=\"arith1\" name=\"plus\"/></OMOBJ>"));
		result = omv.visit(omobj);
		assertTrue(result instanceof OMS);
		assertTrue( ((OMS)result).getName().equals("plus"));
		
		//test OMSTR
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMSTR>Hello</OMSTR></OMOBJ>"));
		result = omv.visit(omobj);
		assertTrue(result instanceof OMSTR);
		assertTrue( ((OMSTR)result).getContent().equals("Hello"));
		
		//test OMV
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMV name=\"x\" /></OMOBJ>"));
		result = omv.visit(omobj);
		assertTrue(result instanceof OMV);
		assertTrue( ((OMV)result).getName().equals("x"));
	
	}
	
	@Test
	public void visitorTestOMS() throws JAXBException{
		Object result;
		OMS oms;

		//the plus function has to be implemented!
		oms = (OMS) unmarshaller.unmarshal(new StringReader(
				"<OMS cd=\"arith1\" name=\"plus\"/>"
				+ ""));
		
		result = omv.visit(oms);
		assertTrue(result instanceof Plus);
	}
	
	@Test
	public void visitorTestOMA() throws JAXBException{
		Object result;
		OMA oma;
		
		//10+5
		//the plus function has to be implemented!
		oma = (OMA) unmarshaller.unmarshal(new StringReader(
				"<OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMI>10</OMI>"
				+ "<OMI>5</OMI>"
				+ "</OMA>"));
		
		result = omv.visit(oma);
		assertTrue(result instanceof OMI);
		assertTrue( ((OMI)result).getValue().equals("15") );
		
	}
	
	@Test
	public void visitorTestNestedOMA() throws JAXBException{
		Object result;
		OMA oma;
		
		//2+3+5
		//the plus function has to be implemented
		oma = (OMA) unmarshaller.unmarshal(new StringReader(
				"<OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd=\"arith1\" name=\"plus\"/><OMI>3</OMI><OMI>2</OMI></OMA>"
				+ "<OMI>5</OMI>"
				+ "</OMA>"));
	
		result = omv.visit(oma);
		assertTrue(result instanceof OMI);
		assertTrue( ((OMI)result).getValue().equals("10"));
	}
	
	@Test(expected = EvaluatorException.class)
	public void visitorTestWithWrongFunction() throws JAXBException{
		Object result;
		OMA oma;
		
		//10+5
		//the plus function has to be implemented!
		oma = (OMA) unmarshaller.unmarshal(new StringReader(
				"<OMA><OMS cd=\"arith1\" name=\"iamunknown\"/>"
				+ "<OMI>10</OMI>"
				+ "<OMI>5</OMI>"
				+ "</OMA>"));
		
		result = omv.visit(oma);
	}
	
	@Test
	public void testNotImplementedOMobject(){
		OMOBJ omobj = new OMOBJ();
		omobj.setOMB(new OMB());
		OMVisitor.visit(omobj);
	}
}
