package de.uni_due.s3.evaluator.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import de.uni_due.s3.openmath.OMSTR;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMConverter;

public class TestExpressionParser {
	
	@Test
	public void testText() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("'test'")),"<OMSTR>test</OMSTR>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'hello world'")),"<OMSTR>hello world</OMSTR>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'Th1S i5 G00D'")),"<OMSTR>Th1S i5 G00D</OMSTR>");
	}
	
	@Test
	public void testFloat() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.1")),"<OMF dec=\"2.1\"/>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.138923893")),"<OMF dec=\"2.138923893\"/>");
	}
	
	@Test
	public void testInteger() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("3")),"<OMI>3</OMI>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("10000")),"<OMI>10000</OMI>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("-10")),"<OMA>"
																			+ "<OMS name=\"unary_minus\" cd=\"arith1\"/>"
																			+ "<OMI>10</OMI>"
																		+ "</OMA>");
	}
	
	@Test
	public void testApplicationUnary() throws Exception{
		
		// TODO this is not openmath standard. The not operation should just work with booleans!
		// see https://www.openmath.org/cd/logic1.xhtml
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("!3")),"<OMA>"
																			+ "<OMS name=\"not\" cd=\"logic1\"/>"
																			+ "<OMI>3</OMI>"
																		+ "</OMA>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("+3")),"<OMA>"
																			+ "<OMS name=\"unary_plus\" cd=\"arith1\"/>"
																			+ "<OMI>3</OMI>"
																		+ "</OMA>");
		throw new Exception("This is not openmath standard");
	}
	
	@Test
	public void testApplicationBinary() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2+3")),"<OMA>"
																			+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																			+ "<OMI>2</OMI>"
																			+ "<OMI>3</OMI>"																			
																		+ "</OMA>");
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0+3")),"<OMA>"
																				+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																				+ "<OMF dec=\"2.0\"/>"
																				+ "<OMI>3</OMI>"																			
																			+ "</OMA>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0/3")),"<OMA>"
																				+ "<OMS name=\"divide\" cd=\"arith1\"/>"
																				+ "<OMF dec=\"2.0\"/>"
																				+ "<OMI>3</OMI>"																			
																			+ "</OMA>");
	}
	
	@Test
	public void testApplicationFunction() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(1.0,3)")),"<OMA>"
																					+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																					+ "<OMF dec=\"1.0\"/>"
																					+ "<OMI>3</OMI>"																			
																				+ "</OMA>");
	
	}
	
	@Test
	public void testApplicationNestedFunction() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(2/3,plus(1.0,3))")),"<OMA>"
																							+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																							+ "<OMA>"
																								+ "<OMS name=\"divide\" cd=\"arith1\"/>"
																								+ "<OMI>2</OMI>"
																								+ "<OMI>3</OMI>"
																							+ "</OMA>"
																							+"<OMA>"
																								+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																								+ "<OMF dec=\"1.0\"/>"
																								+ "<OMI>3</OMI>"																			
																							+ "</OMA>"																			
																						+ "</OMA>");
	
	}
	
	@Test
	public void test() throws JAXBException{
		System.out.println(OMConverter.toString(ExpressionParser.parse("size(set(1,2,3))")));
	}
	
}
