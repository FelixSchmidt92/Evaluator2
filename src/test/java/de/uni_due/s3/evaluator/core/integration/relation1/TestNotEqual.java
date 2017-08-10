package de.uni_due.s3.evaluator.core.integration.relation1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestNotEqual extends TestIntegration {

	static HashMap<Integer, OMOBJ> notEqualFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> notEqualExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		notEqualFillInVariableMap.put(1, ExpressionParser.parse("3", null, null));
		notEqualFillInVariableMap.put(2, ExpressionParser.parse("10", null, null));
		notEqualFillInVariableMap.put(3, ExpressionParser.parse("3.5", null, null));

		notEqualExerciseVariableMap.put("a", ExpressionParser.parse("3", null, null));
		notEqualExerciseVariableMap.put("b", ExpressionParser.parse("10", null, null));
		notEqualExerciseVariableMap.put("c", ExpressionParser.parse("3.5", null, null));
	}

	@Test
	public void testNotEqual1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 != 0", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("30 != 7", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("-10 != 3", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 != 1", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual5() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("0 != 0", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("'a' != 'b'", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual7() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("'' != 0", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual8() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0 != ''", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqual9() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("0 != '0'", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a] != 0", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("30 != [var=a]", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a]!= [var=b]", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithVariables4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[var=a] != [var=a]", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=1] != 0", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("30 != [pos=1]", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithInput3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=1] != [pos=2]", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithInput4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("[pos=1] != [pos=1]", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 != (1 != 0)", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 != (1 != (1 != 0))", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 != (1 != (1 != (1 != (1 != 0))))", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("1 != (1 != (1 != (1 != (1 != 1))))", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithEncapsulation5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 != 0) != 1) != 1) != 1) != 1", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test
	public void testNotEqualWithEncapsulation6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("((((1 != 1) != 1) != 1) != 1) != 1", notEqualExerciseVariableMap, notEqualFillInVariableMap));
	}
	
	@Test(expected=ParserException.class)
	public void testNotEqualWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("1 == ab", notEqualExerciseVariableMap, notEqualFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testNotEqualWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] == 2", notEqualExerciseVariableMap, notEqualFillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testNotEqualWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] == 2", notEqualExerciseVariableMap, notEqualFillInVariableMap);
		fail();
	}
}
