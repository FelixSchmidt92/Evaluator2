package de.uni_due.s3.evaluator2.core.integration.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestToUpperCase extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>hello</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMSTR>hello3</OMSTR></OMOBJ>"));

			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>hello</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMSTR>hello3</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testToUpperCase1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("ABCDEFGHIJKLMNOPQRSTUVWXYZ")), Evaluator
				.evaluate("toUpperCase('ABCDEFGHIJKLMNOPQRSTUVWXYZ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCase2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("1234567890.!\"§$%&/()=?")),
				Evaluator.evaluate("toUpperCase('1234567890.!\"§$%&/()=?')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCase3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("ABCDEFGHIJKLMNOPQRSTUVWXYZ")), Evaluator
				.evaluate("toUpperCase('abcdefghijklmnopqrstuvwxyz')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCase4() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("")),
				Evaluator.evaluate("toUpperCase('')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCase5() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR(" ")),
				Evaluator.evaluate("toUpperCase(' ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCaseWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HELLO")),
				Evaluator.evaluate("toUpperCase('[pos=7]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCaseWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HELLO3")),
				Evaluator.evaluate("toUpperCase('[pos=8]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void TestToUpperCaseWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HELLO")),
				Evaluator.evaluate("toUpperCase('[var=g]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void TestToUpperCaseWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HELLO3")),
				Evaluator.evaluate("toUpperCase('[var=h]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCaseWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("A")),
				Evaluator.evaluate("toUpperCase(toUpperCase('a'))", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testToUpperCaseWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("A")), Evaluator
				.evaluate("toUpperCase(toUpperCase(toUpperCase('a')))", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testToUpperCaseWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toUpperCase(ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToUpperCaseWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toUpperCase('A', 'B')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToUpperCaseWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toUpperCase('A', 'B', 'C')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testToUpperCaseWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toUpperCase('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testToUpperCaseWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("toUpperCase('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}