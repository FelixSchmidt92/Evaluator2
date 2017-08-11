package de.uni_due.s3.evaluator.core.integration.string_jack;

import java.util.HashMap;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCharAt extends TestIntegration {
	static HashMap<Integer, OMOBJ> charAtFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> charAtExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		try {
			charAtFillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMSTR>W</OMSTR></OMOBJ>"));

			charAtExerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMSTR>W</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testCharAt1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("o")),
				Evaluator.evaluate("charAt('Hello World!1',4)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test
	public void testCharAt2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("W")),
				Evaluator.evaluate("charAt('Hello World!1',6)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test
	public void testCharAt3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR(" ")),
				Evaluator.evaluate("charAt('Hello World!1',5)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test
	public void testCharAt4() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("!")),
				Evaluator.evaluate("charAt('Hello World!1',11)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test
	public void testCharAt5() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("1")),
				Evaluator.evaluate("charAt('Hello World!1',12)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testCharAtOutOfBoundsIndexNegative() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('Hello World!1',-1)", charAtExerciseVariableMap, charAtFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testCharAtOutOfBoundsIndexToHigh() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('Hello World!1',55)", charAtExerciseVariableMap, charAtFillInVariableMap);
		fail();
	}

	@Test
	public void testCharAtWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("W")),
				Evaluator.evaluate("charAt('Hello [pos=1]orld!1',6)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test
	public void testCharAtWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("W")),
				Evaluator.evaluate("charAt('Hello [var=a]orld!1',6)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test
	public void testCharAtWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("b")),
				Evaluator.evaluate("charAt(charAt('abc', 1), 0)", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class) //Throws Error, because carAt returns a String! This should not work
	public void testCharAtWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")),
				Evaluator.evaluate("charAt('abc', charAt('00', 0))", charAtExerciseVariableMap, charAtFillInVariableMap));
	}

	@Test (expected=FunctionInvalidArgumentTypeException.class)
	public void testCharAtWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('abcd', a)", charAtExerciseVariableMap, charAtFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCharAtWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('abcd')", charAtExerciseVariableMap, charAtFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCharAtWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('abcd', 0, 2)", charAtExerciseVariableMap, charAtFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testCharAtWithTwoRationalArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt(5, 12)", charAtExerciseVariableMap, charAtFillInVariableMap);
	}

	@Test
	public void testCharAtWithTwoTextArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('abc', '1')", charAtExerciseVariableMap, charAtFillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testCharAtWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('[var=j]', 2)", charAtExerciseVariableMap, charAtFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testCharAtWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("charAt('[pos=42]', 2)", charAtExerciseVariableMap, charAtFillInVariableMap);
		fail();
	}
}