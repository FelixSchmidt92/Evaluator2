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

public class TestBooleanOr extends TestIntegration {

	@Test
	public void testBooleanOr1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 || 1", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOr2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0 || 1", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOr3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 || 0", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOr4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("0 || 0", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOr5() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("(2+4==6) || (3+3==6)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanOrWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=2] || 1", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 || [pos=1]", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testBooleanOrWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=b] || 1", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 || [var=a]", exerciseVariableMap, fillInVariableMap) );
	}
	
	@Test
	public void testBooleanOrWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("0 || (0 || 0)", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 || (1 || 1)", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("0 || (0 || (0 || (0 ||(0 || 0))))", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 || (1 || (1 || (1 || (1 || 1))))", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithEncapsulation5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("((((0 || 0) || 0) || 0) || 0) || 0", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithEncapsulation6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 || 1) || 1) || 1) || 1) || 1", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithDifferentNumbers1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2 || 2", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithDifferentNumbers2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("-1 || 2", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithDifferentNumbers3() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("-1 || 0", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanOrWithDifferentNumbers4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2.34 || 1.11", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test(expected=ParserException.class)
	public void testBooleanOrWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("1 || ab", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testBooleanOrWithWrongInputString() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("1 || 'a'", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testBooleanOrMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] || 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testBooleanOrWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] || 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
