package de.uni_due.s3.evaluator2.core.integration.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSubstring extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>helloworld</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			fillInVariableMap.put(9, OMConverter.toObject("<OMOBJ><OMI>5</OMI></OMOBJ>"));

			exerciseVariableMap.put("suba", OMConverter.toObject("<OMOBJ><OMSTR>helloworld</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMI>5</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testSubstring1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")),
				Evaluator.evaluate("substring('hello world', 0, 5)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstring2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("world")),
				Evaluator.evaluate("substring('hello world', 6, 11)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstring3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR(" ")),
				Evaluator.evaluate("substring('hello world', 5, 6)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstring4() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")),
				Evaluator.evaluate("substring('abcde', 0, 1)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstring5() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("e")),
				Evaluator.evaluate("substring('abcde', 4, 5)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstring6() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("")),
				Evaluator.evaluate("substring('abcde', 4, 4)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstring7() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("e")),
				Evaluator.evaluate("substring('abcde', 4.0, 5)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstringWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("orld")),
				Evaluator.evaluate("substring('[pos=7]', 6, 10)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstringWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")),
				Evaluator.evaluate("substring('abcde', [pos=8], 1)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstringWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("e")),
				Evaluator.evaluate("substring('abcde', 4, [pos=9])", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstringWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("orld")),
				Evaluator.evaluate("substring('[var=suba]', 6, 10)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstringWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")),
				Evaluator.evaluate("substring('abcde', [var=g], 1)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testSubstringWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("e")),
				Evaluator.evaluate("substring('abcde', 4, [var=h])", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testSubstringWithWrongInputSecondAndThirdArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("substring('hello', -1, 6)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testSubstringWithWrongInputSecondArgumentAsPointNumber() throws EvaluatorException, OpenMathException {
		OMSTR result = Evaluator.evaluate("substring('hello', 1.3, 2)", exerciseVariableMap, fillInVariableMap).getOMSTR();
		assertEquals("<OMSTR>e</OMSTR>",result.toString());

	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testSubstringWithWrongInputThirdArgumentAsPointNumber() throws EvaluatorException, OpenMathException {
		OMSTR result = Evaluator.evaluate("substring('hello', 1, 2.9)", exerciseVariableMap, fillInVariableMap).getOMSTR();
		assertEquals("<OMSTR>e</OMSTR>",result.toString());
	}

	@Test(expected = ParserException.class)
	public void testSubstringWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("substring(ab, ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testSubstringWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("substring('hello')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test
	public void testSubstringWithTwoArguments() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("llo")),
				Evaluator.evaluate("substring('hello', 2)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testSubstringWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("substring('[var=j]', '[var=j]', '[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testSubstringWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("substring('[pos=42]', '[pos=42]', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}