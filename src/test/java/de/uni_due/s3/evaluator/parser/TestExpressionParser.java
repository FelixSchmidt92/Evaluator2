package de.uni_due.s3.evaluator.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import de.uni_due.s3.JAXBOpenMath.OMUtils.OMConverter;
import de.uni_due.s3.JAXBOpenMath.openmath.OMA;
import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.evaluator.exceptions.ParserException;
import de.uni_due.s3.evaluator.exceptions.UndefinedExerciseVariableException;

public class TestExpressionParser {
	
	private Map<String,OMOBJ> exerciseVariableMap;
	private Map<Integer,OMOBJ> fillInVariableMap;
	
	@Before
	public void init() throws JAXBException{
		exerciseVariableMap = new HashMap<String,OMOBJ>();
		fillInVariableMap = new HashMap<Integer,OMOBJ>();
		
		exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
		fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMA><OMS cd=\"arith1\" name=\"plus\" /><OMI>3</OMI><OMI>5</OMI></OMA></OMOBJ>"));
		
	}
	@Test
	public void testText() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("'test'",null,null)),"<OMOBJ><OMSTR>test</OMSTR></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'hello world'",null,null)),"<OMOBJ><OMSTR>hello world</OMSTR></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'Th1S i5 G00D'",null,null)),"<OMOBJ><OMSTR>Th1S i5 G00D</OMSTR></OMOBJ>");
	}
	
	@Test
	public void testFloat() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.1",null,null)),"<OMOBJ><OMF dec=\"2.1\"/></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.138923893",null,null)),"<OMOBJ><OMF dec=\"2.138923893\"/></OMOBJ>");
	}
	
	@Test
	public void testInteger() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("3",null,null)),"<OMOBJ><OMI>3</OMI></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("10000",null,null)),"<OMOBJ><OMI>10000</OMI></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("-10",null,null)),"<OMOBJ><OMA>"
																			+ "<OMS name=\"unary_minus\" cd=\"arith1\"/>"
																			+ "<OMI>10</OMI>"
																		+ "</OMA></OMOBJ>");
	}
	
	@Test
	public void testApplicationUnary() throws Exception{
		assertEquals(OMConverter.toString(ExpressionParser.parse("!3",null,null)),"<OMOBJ><OMA>"
																			+ "<OMS name=\"not\" cd=\"logic1\"/>"
																			+ "<OMI>3</OMI>"
																		+ "</OMA></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("+3",null,null)),"<OMOBJ><OMA>"
																			+ "<OMS name=\"unary_plus\" cd=\"arith1\"/>"
																			+ "<OMI>3</OMI>"
																		+ "</OMA></OMOBJ>");
	
	}
	
	@Test
	public void testApplicationBinary() throws JAXBException{
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2+3",null,null)),"<OMOBJ><OMA>"
																			+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																			+ "<OMI>2</OMI>"
																			+ "<OMI>3</OMI>"																			
																		+ "</OMA></OMOBJ>");
		
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0+3",null,null)),"<OMOBJ><OMA>"
																				+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																				+ "<OMF dec=\"2.0\"/>"
																				+ "<OMI>3</OMI>"																			
																			+ "</OMA></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0/3",null,null)),"<OMOBJ><OMA>"
																				+ "<OMS name=\"divide\" cd=\"arith1\"/>"
																				+ "<OMF dec=\"2.0\"/>"
																				+ "<OMI>3</OMI>"																			
																			+ "</OMA></OMOBJ>");
	}
	
	@Test
	public void testApplicationFunction() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(1.0,3)",null,null)),"<OMOBJ><OMA>"
																					+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																					+ "<OMF dec=\"1.0\"/>"
																					+ "<OMI>3</OMI>"																			
																				+ "</OMA></OMOBJ>");
	
	}
	
	@Test
	public void testApplicationNestedFunction() throws JAXBException{
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(2/3,plus(1.0,3))",null,null)),"<OMOBJ><OMA>"
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
	
	
	@Test
	public void testExerciseVariable() throws JAXBException{
		assertTrue( ExpressionParser.parse("[var=a]", exerciseVariableMap, fillInVariableMap)!= null);
		assertEquals("<OMOBJ><OMI>3</OMI></OMOBJ>",OMConverter.toString(ExpressionParser.parse("[var=a]", exerciseVariableMap, fillInVariableMap)));
		assertEquals("<OMOBJ><OMI>3</OMI></OMOBJ>",OMConverter.toString(ExpressionParser.parse("'[var=a]'", exerciseVariableMap, fillInVariableMap)));
		assertEquals("<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>4</OMI></OMA></OMOBJ>",OMConverter.toString(ExpressionParser.parse("[var=a] + 4", exerciseVariableMap, fillInVariableMap)));
		
		//FIXME
		assertEquals("<OMOBJ><OMSTR>i am in a string 3</OMSTR></OMOBJ>",OMConverter.toString(ExpressionParser.parse("'i am in a string [var=a]'", exerciseVariableMap, fillInVariableMap)));
		
	}
	
	@Test(expected=ParserException.class)
	public void testExerciseVariableWithIllegalName(){
		ExpressionParser.parse("[var=abcd?!''§$%&]", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testExerciseVariableWithException(){
		ExpressionParser.parse("[var=abcd]", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test
	public void testFillInVariable() throws JAXBException{
		assertTrue( ExpressionParser.parse("[var=a]", exerciseVariableMap, fillInVariableMap)!= null);
		assertEquals("<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA></OMOBJ>",OMConverter.toString(ExpressionParser.parse("[pos=1]", exerciseVariableMap, fillInVariableMap)));
		assertEquals("<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA></OMOBJ>",OMConverter.toString(ExpressionParser.parse("'[pos=1]'", exerciseVariableMap, fillInVariableMap)));
		assertEquals("<OMOBJ><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA><OMI>4</OMI></OMA></OMOBJ>",OMConverter.toString(ExpressionParser.parse("[pos=1] + 4", exerciseVariableMap, fillInVariableMap)));
		
		//FIXME
		assertEquals("<OMOBJ><OMSTR>i am in a string 8</OMSTR></OMOBJ>",OMConverter.toString(ExpressionParser.parse("'i am in a string [pos=1]'", exerciseVariableMap, fillInVariableMap)));
		
	}
	
	@Test
	public void testMathTerm() throws JAXBException{
		System.out.println(OMConverter.toString(ExpressionParser.parse("3+(5-7)",null,null)));
		assertEquals(OMConverter.toString(ExpressionParser.parse("3+(5-7)",null,null)),"<OMOBJ><OMA>"
				+ "<OMS name=\"plus\" cd=\"arith1\"/>"
				+ "<OMI>3</OMI>"
				+"<OMA>"
					+ "<OMS name=\"minus\" cd=\"arith1\"/>"
					+ "<OMI>5</OMI>"
					+ "<OMI>7</OMI>"
				+ "</OMA>"																			
			+ "</OMA></OMOBJ>");
	}
	
}
