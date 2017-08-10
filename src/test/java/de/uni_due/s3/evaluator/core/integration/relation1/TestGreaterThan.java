package de.uni_due.s3.evaluator.core.integration.relation1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGreaterThan extends TestIntegration {

	static HashMap<Integer, OMOBJ> greaterThanFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> greaterThanExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		greaterThanFillInVariableMap.put(1, ExpressionParser.parse("3", null, null));
		greaterThanFillInVariableMap.put(2, ExpressionParser.parse("1", null, null));

		greaterThanExerciseVariableMap.put("a", ExpressionParser.parse("8", null, null));
		greaterThanExerciseVariableMap.put("b", ExpressionParser.parse("25", null, null));
	}
	
	@Test
	public void testGreaterThan1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("22>8", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThan2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("2>8", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThan3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("5.7>3.5", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThan4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1>-1", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThan5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0.2>0", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=b]>8", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[var=a]>9", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=b]>[var=a]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithVariables4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[var=a]>[var=b]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithVariables5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("22>[var=a]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithVariables6() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("2>[var=a]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=1]>2", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("34>[pos=2]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithInput3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[pos=2]>2", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithInput4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=1]>[pos=2]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithInput5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[pos=2]>[pos=1]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithInput6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("22>[pos=1]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithInput7() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("2>[pos=1]", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}

	@Test 
	public void testGreaterThanWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 > (1 > 2)", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 > (1 > (1 > 2))", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 > (1 > (1 > (1 > (1 > 2))))", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test 
	public void testGreaterThanWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 > 1) > 1) > 1) > 1) > -1", greaterThanExerciseVariableMap, greaterThanFillInVariableMap));
	}
	
	@Test(expected=ParserException.class)
	public void testGreaterThanWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 > ab", greaterThanExerciseVariableMap, greaterThanFillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testGreaterThanWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 > 'a'", greaterThanExerciseVariableMap, greaterThanFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testGreaterThanWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] > 2", greaterThanExerciseVariableMap, greaterThanFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testGreaterThanWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] > 2", greaterThanExerciseVariableMap, greaterThanFillInVariableMap);
		fail();
	}
}
