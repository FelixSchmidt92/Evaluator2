package de.uni_due.s3.evaluator.core.integration.relation1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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

public class TestGreaterThanOrEqual extends TestIntegration {

	static HashMap<Integer, OMOBJ> greaterThanOrEqualFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> greaterThanOrEqualExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		greaterThanOrEqualFillInVariableMap.put(1, ExpressionParser.parse("3", null, null));
		greaterThanOrEqualFillInVariableMap.put(2, ExpressionParser.parse("1", null, null));

		greaterThanOrEqualExerciseVariableMap.put("a", ExpressionParser.parse("8", null, null));
		greaterThanOrEqualExerciseVariableMap.put("b", ExpressionParser.parse("25", null, null));
	}
	
	@Test
	public void testGreaterThanOrEqual1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("22>=8", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqual2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("8>=8", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqual3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("2>=8", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqual4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("9>=8", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqual5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("7>=45", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqual6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("7>=7", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqual7() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0>=-0.2", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqual8() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0>=0", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("22>=[var=a]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("2>=[var=a]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=b]>=8", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithVariables4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a]>=8", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithVariables5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("23>=[var=b]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithVariables6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a]>=[var=a]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithVariables7() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=b]>=[var=a]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}

	@Test
	public void testGreaterThanOrEqualWithVariables8() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[var=a]>=[var=b]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("22>=[pos=1]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("3>=[pos=1]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithInput3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("2>=[pos=1]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithInput4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("5>=[pos=2]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithInput5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[pos=2]>=5", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithInput6() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[pos=2]>=[pos=1]", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}

	@Test
	public void testGreaterThanOrEqualWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 >= (1 >= 2)", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test 
	public void testGreaterThanOrEqualWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("0 >= (0 >= (0 >= 1))", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test 
	public void testGreaterThanOrEqualWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("0 >= (0 >= (0 >= (0 >= (0 >= 1))))", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	}
	
	@Test
	public void testGreaterThanOrEqualWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 >= 1) >= 1) >= 1) >= 1) >= 1", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap));
	
	}
	
	@Test(expected=ParserException.class)
	public void testGreaterThanOrEqualWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 >= ab", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testGreaterThanOrEqualWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 >= 'a'", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testGreaterThanOrEqualWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] >= 2", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testGreaterThanOrEqualWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] >= 2", greaterThanOrEqualExerciseVariableMap, greaterThanOrEqualFillInVariableMap);
		fail();
	}
}
