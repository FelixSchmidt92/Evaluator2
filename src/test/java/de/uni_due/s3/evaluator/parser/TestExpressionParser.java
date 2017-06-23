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
import de.uni_due.s3.evaluator.core.OMConverter;
import de.uni_due.s3.openmath.OMA;

public class TestExpressionParser {
	
	@Test
	public void testText() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("'test'")),"<OMOBJ><OMSTR>test</OMSTR></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'hello world'")),"<OMOBJ><OMSTR>hello world</OMSTR></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'Th1S i5 G00D'")),"<OMOBJ><OMSTR>Th1S i5 G00D</OMSTR></OMOBJ>");
	}
	
	@Test
	public void testFloat() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.1")),"<OMOBJ><OMF dec=\"2.1\"/></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.138923893")),"<OMOBJ><OMF dec=\"2.138923893\"/></OMOBJ>");
	}
	
	@Test
	public void testInteger() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("3")),"<OMOBJ><OMI>3</OMI></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("10000")),"<OMOBJ><OMI>10000</OMI></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("-10")),"<OMOBJ><OMA>"
																			+ "<OMS name=\"unary_minus\" cd=\"arith1\"/>"
																			+ "<OMI>10</OMI>"
																		+ "</OMA></OMOBJ>");
	}
	
	@Test
	public void testApplicationUnary() throws Exception{
		
		// TODO this is not openmath standard. The not operation should just work with booleans!
		// see https://www.openmath.org/cd/logic1.xhtml
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("!3")),"<OMOBJ><OMA>"
																			+ "<OMS name=\"not\" cd=\"logic1\"/>"
																			+ "<OMI>3</OMI>"
																		+ "</OMA></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("+3")),"<OMOBJ><OMA>"
																			+ "<OMS name=\"unary_plus\" cd=\"arith1\"/>"
																			+ "<OMI>3</OMI>"
																		+ "</OMA></OMOBJ>");
		throw new Exception("This is not openmath standard");
	}
	
	@Test
	public void testApplicationBinary() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2+3")),"<OMOBJ><OMA>"
																			+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																			+ "<OMI>2</OMI>"
																			+ "<OMI>3</OMI>"																			
																		+ "</OMA></OMOBJ>");
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0+3")),"<OMOBJ><OMA>"
																				+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																				+ "<OMF dec=\"2.0\"/>"
																				+ "<OMI>3</OMI>"																			
																			+ "</OMA></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0/3")),"<OMOBJ><OMA>"
																				+ "<OMS name=\"divide\" cd=\"arith1\"/>"
																				+ "<OMF dec=\"2.0\"/>"
																				+ "<OMI>3</OMI>"																			
																			+ "</OMA></OMOBJ>");
	}
	
	@Test
	public void testApplicationFunction() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(1.0,3)")),"<OMOBJ><OMA>"
																					+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																					+ "<OMF dec=\"1.0\"/>"
																					+ "<OMI>3</OMI>"																			
																				+ "</OMA></OMOBJ>");
	
	}
	
	@Test
	public void testApplicationNestedFunction() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(2/3,plus(1.0,3))")),"<OMOBJ><OMA>"
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
																						+ "</OMA></OMOBJ>");
	
	}
	
}
