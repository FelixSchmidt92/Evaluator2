package de.uni_due.s3.evaluator.core;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.JAXBOpenMath.OMUtils.OMCreator;
import de.uni_due.s3.JAXBOpenMath.openmath.OMA;
import de.uni_due.s3.JAXBOpenMath.openmath.OMB;
import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.JAXBOpenMath.openmath.OMV;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Plus;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMOBJChildNotSupportedException;



public class TestOMExecuter {

	private static Unmarshaller unmarshaller;

	
	@BeforeClass
	public static void init() throws JAXBException{
		JAXBContext context = JAXBContext
				.newInstance("de.uni_due.s3.JAXBOpenMath.openmath");

		unmarshaller = context.createUnmarshaller();
	}
	
	@Test
	public void visitorTestOMOBJ() throws JAXBException{
		OMOBJ result;
		OMOBJ omobj;
		//test OMI
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMI>5</OMI></OMOBJ>"));
		result = OMExecutor.execute(omobj);
		assertEquals( result.getOMI().getValue(),"5");
		
		//test OMF
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMF dec=\"5.12345\"></OMF></OMOBJ>"));
		result = OMExecutor.execute(omobj);
		assertTrue( result.getOMF().getDec().doubleValue() == 5.12345d);
		
		//test OMS
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMS cd=\"arith1\" name=\"plus\"/></OMOBJ>"));
		result = OMExecutor.execute(omobj);
		assertTrue( result.getOMS().getName().equals("plus"));
		
		//test OMSTR
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMSTR>Hello</OMSTR></OMOBJ>"));
		result = OMExecutor.execute(omobj);
	
		assertTrue( result.getOMSTR().getContent().equals("Hello"));
		
		//test OMV
		omobj = (OMOBJ) unmarshaller.unmarshal(new StringReader(
				"<OMOBJ><OMV name=\"x\" /></OMOBJ>"));
		result = OMExecutor.execute(omobj);
		assertTrue( result.getOMV().getName().equals("x"));
	
	}
	
	@Test
	public void visitorTestOMA() throws JAXBException{
		OMOBJ result;
		OMA oma;
		
		//10+5
		//the plus function has to be implemented!
		oma = (OMA) unmarshaller.unmarshal(new StringReader(
				"<OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMI>10</OMI>"
				+ "<OMI>5</OMI>"
				+ "</OMA>"));
		
		result = OMExecutor.execute(OMCreator.createOMOBJ(oma));
		assertTrue( result.getOMI().getValue().equals("15") );
		
	}
	
	@Test
	public void visitorTestNestedOMA() throws JAXBException{
		OMOBJ result;
		OMA oma;
		
		//2+3+5
		//the plus function has to be implemented
		oma = (OMA) unmarshaller.unmarshal(new StringReader(
				"<OMA><OMS cd=\"arith1\" name=\"plus\"/>"
				+ "<OMA><OMS cd=\"arith1\" name=\"plus\"/><OMI>3</OMI><OMI>2</OMI></OMA>"
				+ "<OMI>5</OMI>"
				+ "</OMA>"));
	
		result = OMExecutor.execute(OMCreator.createOMOBJ(oma));
		assertTrue( result.getOMI().getValue().equals("10"));
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
		
		result = OMExecutor.execute(OMCreator.createOMOBJ(oma));
	}
	
	@Test(expected=OMOBJChildNotSupportedException.class)
	public void testNotImplementedOMobject(){
		OMOBJ omobj = new OMOBJ();
		omobj.setOMB(new OMB());
		OMExecutor.execute(omobj);
	}
}
