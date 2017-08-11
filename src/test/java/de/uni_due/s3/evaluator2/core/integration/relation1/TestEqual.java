package de.uni_due.s3.evaluator2.core.integration.relation1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqual extends TestIntegration {

	static HashMap<Integer, OMOBJ> equalFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> equalExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		equalFillInVariableMap.put(1, ExpressionParser.parse("7", null, null));
		equalFillInVariableMap.put(2, ExpressionParser.parse("2", null, null));

		equalExerciseVariableMap.put("a", ExpressionParser.parse("7", null, null));
		equalExerciseVariableMap.put("b", ExpressionParser.parse("2", null, null));
	}
	
	@Test
	public void testEqual0() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 == 1.0", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1/2 == 0.5", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0.7 == 7/10", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("4/2 == 2", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("'world of math' == 'world of math'", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("'' == ''", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual6() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("'' == 1", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual7() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 = ''", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqual8() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 = '1'", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a] == 7", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2 == [var=b]", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=1] == 7", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2 == [pos=2]", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 == (1 == 0)", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 == (1 == (1 == 0))", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 == (1 == (1 == (1 == (1 == 0))))", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 == (1 == (1 == (1 == (1 == 1))))", equalExerciseVariableMap, equalFillInVariableMap));
	}

	@Test
	public void testEqualWithEncapsulation5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("((((1 == 0) == 1) == 1) == 1) == 1", equalExerciseVariableMap, equalFillInVariableMap));
	}
	
	@Test
	public void testEqualWithEncapsulation6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 == 1) == 1) == 1) == 1) == 1", equalExerciseVariableMap, equalFillInVariableMap));
	}

	@Test(expected=ParserException.class)
	public void testEqualWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("1 == ab", equalExerciseVariableMap, equalFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testEqualWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] == 2", equalExerciseVariableMap, equalFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testEqualWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] == 2", equalExerciseVariableMap, equalFillInVariableMap);
		fail();
	}
}
