package de.uni_due.s3.evaluator2.core.integration.relation1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLessThanOrEqual extends TestIntegration {

	static HashMap<Integer, OMOBJ> lessThanOrEqualFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> lessThanOrEqualExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		lessThanOrEqualFillInVariableMap.put(1, ExpressionParser.parse("3", null, null));
		lessThanOrEqualFillInVariableMap.put(2, ExpressionParser.parse("1", null, null));
		lessThanOrEqualFillInVariableMap.put(3, ExpressionParser.parse("2.5", null, null));

		lessThanOrEqualExerciseVariableMap.put("a", ExpressionParser.parse("8", null, null));
		lessThanOrEqualExerciseVariableMap.put("b", ExpressionParser.parse("25", null, null));
		lessThanOrEqualExerciseVariableMap.put("c", ExpressionParser.parse("2.5", null, null));
	}

	@Test
	public void testLessThanOrEqual1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("22<=8", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqual2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("8<=8", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqual3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2<=8", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqual4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("8<=9", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqual5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("45<=7", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqual6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("7<=7", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqual7() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("-0.2<=0", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqual8() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0<=0", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("22<=[var=a]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("3<=[var=a]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2<=[var=a]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("8<=[var=b]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
		
	@Test
	public void testLessThanOrEqualWithVariables5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("8<=[var=a]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables6() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[var=b]<=23", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables7() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a]<=[var=a]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables8() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a]<=[var=b]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithVariables9() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[var=b]<=[var=a]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("22<=[pos=1]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("3<=[pos=1]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithInput3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2<=[pos=1]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithInput4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("3<=[pos=1]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithInput5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=2]<=5", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithInput6() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("5<=[pos=2]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithInput7() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[pos=1]<=[pos=2]", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 <= (1 <= 2)", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 <= (1 <= (0 <= 1))", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0 <= (0 <= (0 <= (0 <= (0 <= 1))))", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testLessThanOrEqualWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 <= 1) <= 1) <= 1) <= 1) <= 1", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap));
	}
	
	@Test(expected=ParserException.class)
	public void testLessThanOrEqualWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 <= ab", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testLessThanOrEqualWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 <= 'a'", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testLessThanOrEqualWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] <= 2", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testLessThanOrEqualWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] <= 2", lessThanOrEqualExerciseVariableMap, lessThanOrEqualFillInVariableMap);
		fail();
	}
}
