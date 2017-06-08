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

import de.uni_due.s3.openmath.OMSTR;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMA;

public class TestExpressionParser {
	
	@Test
	public void testText(){
		assertEquals(((OMSTR)ExpressionParser.parse("'test'")).getContent(), "test");
		assertEquals(((OMSTR)ExpressionParser.parse("'test123'")).getContent(), "test123");
	}
	
	@Test
	public void testFloat(){
		assertTrue(((OMF)ExpressionParser.parse("2.1")).getDec() == 2.1);
		assertTrue(((OMF)ExpressionParser.parse("'2.138923893'")).getDec()== 2.138923893);
	}
	
	@Test
	public void testInteger(){
		assertEquals(((OMI)ExpressionParser.parse("3")).getValue() ,"3");
		assertEquals(((OMI)ExpressionParser.parse("'2233'")).getValue(), "2233");
	}
	
	@Test
	public void testUnary(){
		assertEquals(((OMS)(((OMA)ExpressionParser.parse("!3")).getOmel().get(0))).getName(), "not");
		assertEquals(((OMA)ExpressionParser.parse("+1")).getOmel().size(), 2);
	}
	
	@Test
	public void testBinary(){
		assertTrue(((OMA)ExpressionParser.parse("1+2")).getOmel().get(0) instanceof OMS);
		assertTrue(((OMA)ExpressionParser.parse("1+2")).getOmel().get(1) instanceof OMI);
		assertTrue(((OMA)ExpressionParser.parse("1+2")).getOmel().get(2) instanceof OMI);
	}
	
	@Test
	public void testFunction() throws JAXBException{
		assertEquals(((OMA)ExpressionParser.parse("plus(1, 3)")).getOmel().size(), 3);
	}
	

	@Test
	public void testParser() {
		assertTrue(ExpressionParser.parse("--1").equals(/*OpenMathObjectCreator.createOpenMathInteger(+1)*/null));
	}
	
	@Test
	public void testIntegerValue() {
		assertTrue(ExpressionParser.parse("-1").equals(/*OpenMathObjectCreator.createOpenMathInteger(-1)*/null));
		assertTrue(ExpressionParser.parse("'1'").equals(/*OpenMathObjectCreator.createOpenMathInteger(1)*/null));
		assertTrue(ExpressionParser.parse("1").equals(/*OpenMathObjectCreator.createOpenMathInteger(1)*/null));
		assertTrue(ExpressionParser.parse("'5'").equals(/*OpenMathObjectCreator.createOpenMathInteger(5)*/null));
		assertTrue(ExpressionParser.parse("5").equals(/*OpenMathObjectCreator.createOpenMathInteger(5)*/null));
	}
	
	@Test
	public void testUnaryOperator() {
		assertTrue(ExpressionParser.parse("'-1'").equals(/*OpenMathObjectCreator.createOpenMathInteger(-1)*/null));
		assertTrue(ExpressionParser.parse("-1").equals(/*OpenMathObjectCreator.createOpenMathInteger(-1)*/null));
		assertTrue(ExpressionParser.parse("'+1'").equals(/*OpenMathObjectCreator.createOpenMathInteger(1)*/null));
		assertTrue(ExpressionParser.parse("+1").equals(/*OpenMathObjectCreator.createOpenMathInteger(1)*/null));
	}
}
