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
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLessThan extends TestIntegration {

	static HashMap<Integer, OMOBJ> lessThanFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> lessThanExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException, ErroneousExerciseVariableException, OpenMathException {
		lessThanFillInVariableMap.put(1, ExpressionParser.parse("3", null, null));
		lessThanFillInVariableMap.put(2, ExpressionParser.parse("1", null, null));

		lessThanExerciseVariableMap.put("a", ExpressionParser.parse("8", null, null));
		lessThanExerciseVariableMap.put("b", ExpressionParser.parse("25", null, null));
	}

	@Test
	public void testLessThan1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("8<22", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThan2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("22<8", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThan3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("3.5<5.7", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThan4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("-1<1", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThan5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("-0.2<0", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithExerciseVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("8<[var=b]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithExerciseVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("9<[var=a]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithExerciseVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("22<[var=a]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithExerciseVariables4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[var=a]<100", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithExerciseVariables5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2<[var=a]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithExerciseVariables6() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("[var=a]<[var=b]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithExerciseVariables7() throws EvaluatorException, OpenMathException {
		assertTrue(
				!Evaluator.getBooleanResult("[var=b]<[var=a]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2<[pos=1]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("[pos=2]<34", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithInput3() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("2<[pos=2]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithInput4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("22<[pos=1]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithInput5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("2<[pos=1]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithInput6() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("[pos=2]<[pos=1]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithInput7() throws EvaluatorException, OpenMathException {
		assertTrue(
				!Evaluator.getBooleanResult("[pos=1]<[pos=2]", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("1 < (1 < 2)", lessThanExerciseVariableMap, lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0 < (0 < (0 < 1))", lessThanExerciseVariableMap,
				lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("0 < (0 < (0 < (0 < (0 < 1))))", lessThanExerciseVariableMap,
				lessThanFillInVariableMap));
	}

	@Test
	public void testLessThanWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("((((1 < 1) < 1) < 1) < 1) < 1", lessThanExerciseVariableMap,
				lessThanFillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testLessThanWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 < ab", lessThanExerciseVariableMap, lessThanFillInVariableMap);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testLessThanWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("6 < 'a'", lessThanExerciseVariableMap, lessThanFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testLessThanWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[var=j] < 2", lessThanExerciseVariableMap, lessThanFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testLessThanWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("[pos=42] < 2", lessThanExerciseVariableMap, lessThanFillInVariableMap);
		fail();
	}
}
