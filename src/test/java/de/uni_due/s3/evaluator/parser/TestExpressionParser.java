package de.uni_due.s3.evaluator.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;

public class TestExpressionParser {

	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;

	@Before
	public void init() throws JAXBException {
		exerciseVariableMap = new HashMap<String, OMOBJ>();
		fillInVariableMap = new HashMap<Integer, OMOBJ>();

		exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>3</OMI></OMOBJ>"));
		fillInVariableMap.put(1, OMConverter
				.toObject("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA><OMS cd=\"arith1\" name=\"plus\" /><OMI>3</OMI><OMI>5</OMI></OMA></OMOBJ>"));

	}

	@Test
	public void testText() throws JAXBException, FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		assertEquals(OMConverter.toString(ExpressionParser.parse("'test'", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMSTR>test</OMSTR></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'hello world'", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMSTR>hello world</OMSTR></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("'Th1S i5 G00D'", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMSTR>Th1S i5 G00D</OMSTR></OMOBJ>");
	}

	@Test
	public void testFloat() throws JAXBException, FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {

		assertEquals(OMConverter.toString(ExpressionParser.parse("2.1", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMF dec=\"2.1\"/></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.138923893", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMF dec=\"2.138923893\"/></OMOBJ>");
	}

	@Test
	public void testInteger() throws JAXBException, FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {

		assertEquals(OMConverter.toString(ExpressionParser.parse("3", null, null)), "<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>3</OMI></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("10000", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>10000</OMI></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("-10", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"unary_minus\" cd=\"arith1\"/>" + "<OMI>10</OMI>" + "</OMA></OMOBJ>");
	}

	@Test
	public void testApplicationUnary() throws Exception {
		assertEquals(OMConverter.toString(ExpressionParser.parse("!3", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"not\" cd=\"logic1\"/>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("-3", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"unary_minus\" cd=\"arith1\"/>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>");

	}

	@Test
	public void testApplicationBinary() throws JAXBException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {

		assertEquals(OMConverter.toString(ExpressionParser.parse("2+3", null, null)), "<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>"
				+ "<OMS name=\"plus\" cd=\"arith1\"/>" + "<OMI>2</OMI>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>");

		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0+3", null, null)), "<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>"
				+ "<OMS name=\"plus\" cd=\"arith1\"/>" + "<OMF dec=\"2.0\"/>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>");
		assertEquals(OMConverter.toString(ExpressionParser.parse("2.0/3", null, null)), "<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>"
				+ "<OMS name=\"divide\" cd=\"arith1\"/>" + "<OMF dec=\"2.0\"/>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>");
	}

	@Test
	public void testApplicationFunction() throws JAXBException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(1.0,3)", null, null)), "<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>"
				+ "<OMS name=\"plus\" cd=\"arith1\"/>" + "<OMF dec=\"1.0\"/>" + "<OMI>3</OMI>" + "</OMA></OMOBJ>");

	}

	@Test
	public void testApplicationNestedFunction() throws JAXBException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		assertEquals(OMConverter.toString(ExpressionParser.parse("plus(2/3,plus(1.0,3))", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"plus\" cd=\"arith1\"/>" + "<OMA>" + "<OMS name=\"divide\" cd=\"arith1\"/>"
						+ "<OMI>2</OMI>" + "<OMI>3</OMI>" + "</OMA>" + "<OMA>" + "<OMS name=\"plus\" cd=\"arith1\"/>"
						+ "<OMF dec=\"1.0\"/>" + "<OMI>3</OMI>" + "</OMA>" + "</OMA></OMOBJ>");
	}

	@Test
	public void testExerciseVariable() throws JAXBException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		assertTrue(ExpressionParser.parse("[var=a]", exerciseVariableMap, fillInVariableMap) != null);
		assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>3</OMI></OMOBJ>",
				OMConverter.toString(ExpressionParser.parse("[var=a]", exerciseVariableMap, fillInVariableMap)));
		assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMI>3</OMI></OMOBJ>",
				OMConverter.toString(ExpressionParser.parse("'[var=a]'", exerciseVariableMap, fillInVariableMap)));
		assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>4</OMI></OMA></OMOBJ>",
				OMConverter.toString(ExpressionParser.parse("[var=a] + 4", exerciseVariableMap, fillInVariableMap)));

		// FIXME
		assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA><OMS name=\"textValue\" cd=\"jacksrtring1\"/><OMSTR>i am in a string </OMSTR><OMI>3</OMI><OMSTR> and in another </OMSTR><OMI>3</OMI></OMA></OMOBJ>", OMConverter.toString(
				ExpressionParser.parse("'i am in a string [var=a] and in another [var=a]'", exerciseVariableMap, fillInVariableMap)));

	}

	@Test(expected = ParserException.class)
	public void testExerciseVariableWithIllegalName() throws FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		ExpressionParser.parse("[var=abcd?!''§$%&]", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testExerciseVariableWithException() throws FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		ExpressionParser.parse("[var=abcd]", exerciseVariableMap, fillInVariableMap);
	}

	@Test
	public void testTest() throws FunctionNotImplementedException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		ExpressionParser.parse("'Text'", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test
	public void testFillInVariable() throws JAXBException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		assertTrue(ExpressionParser.parse("[var=a]", exerciseVariableMap, fillInVariableMap) != null);
		assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA></OMOBJ>",
				OMConverter.toString(ExpressionParser.parse("[pos=1]", exerciseVariableMap, fillInVariableMap)));
		assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA></OMOBJ>",
				OMConverter.toString(ExpressionParser.parse("'[pos=1]'", exerciseVariableMap, fillInVariableMap)));
		assertEquals(
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA><OMI>4</OMI></OMA></OMOBJ>",
				OMConverter.toString(ExpressionParser.parse("[pos=1] + 4", exerciseVariableMap, fillInVariableMap)));

		// FIXME
		assertEquals("<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA><OMS name=\"textValue\" cd=\"jacksrtring1\"/><OMSTR>i am in a string </OMSTR><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA><OMSTR> anotherone</OMSTR><OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>3</OMI><OMI>5</OMI></OMA><OMSTR> and last one</OMSTR></OMA></OMOBJ>", OMConverter.toString(
				ExpressionParser.parse("'i am in a string [pos=1] anotherone[pos=1] and last one'", exerciseVariableMap, fillInVariableMap)));

	}

	@Test
	public void testMathTerm() throws JAXBException, FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		assertEquals(OMConverter.toString(ExpressionParser.parse("3+(5-7)", null, null)),
				"<OMOBJ xmlns=\"http://www.openmath.org/OpenMath\"><OMA>" + "<OMS name=\"plus\" cd=\"arith1\"/>" + "<OMI>3</OMI>" + "<OMA>"
						+ "<OMS name=\"minus\" cd=\"arith1\"/>" + "<OMI>5</OMI>" + "<OMI>7</OMI>" + "</OMA>"
						+ "</OMA></OMOBJ>");
	}

}
