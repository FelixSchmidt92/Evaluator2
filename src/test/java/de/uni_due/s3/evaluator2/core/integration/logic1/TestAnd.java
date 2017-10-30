package de.uni_due.s3.evaluator2.core.integration.logic1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestAnd extends TestIntegration {

	@Test
	public void testBooleanAnd1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 && 1", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAnd2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("0 && 1", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAnd3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 && 0", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAnd4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("0 && 0", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAnd5() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("(2+4==6) && (3+3==6)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=2] && 1", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithVariables2() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("0 && [pos=2]", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=b] && 1", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithInput2() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("0 && [var=a]", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("1 && (1 && 0)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 && (1 && 1)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("1 && (1 && (1 && (1 &&(1 && 0))))", exerciseVariableMap,
				fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 && (1 && (1 && (1 && (1 && 1))))", exerciseVariableMap,
				fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithEncapsulation5() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("((((0 && 1) && 1) && 1) && 1) && 1", exerciseVariableMap,
				fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithEncapsulation6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 && 1) && 1) && 1) && 1) && 1", exerciseVariableMap,
				fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithDifferentNumbers1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2 && 2", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithDifferentNumbers2() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("-1 && 2", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithDifferentNumbers3() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("-1 && 0", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanAndWithDifferentNumbers4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2.34 && 1.11", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testBooleanAndWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("1 && ab", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testBooleanAndWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("1 && 'a'", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testBooleanAndMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] && 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testBooleanAndWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] && 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
