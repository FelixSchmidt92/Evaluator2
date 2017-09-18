package de.uni_due.s3.evaluator2.core.integration.openmath_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetNumerator extends TestIntegration {

	static HashMap<Integer, OMOBJ> getNumeratorFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> getNumeratorExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		getNumeratorFillInVariableMap.put(1, ExpressionParser.parse("0", null, null));
		getNumeratorFillInVariableMap.put(2, ExpressionParser.parse("20/3", null, null));
		getNumeratorFillInVariableMap.put(3, ExpressionParser.parse("10.3", null, null));
		getNumeratorFillInVariableMap.put(4, ExpressionParser.parse("-5", null, null));
		getNumeratorFillInVariableMap.put(5, ExpressionParser.parse("5", null, null));

		getNumeratorExerciseVariableMap.put("a", ExpressionParser.parse("0", null, null));
		getNumeratorExerciseVariableMap.put("b", ExpressionParser.parse("20/3", null, null));
		getNumeratorExerciseVariableMap.put("c", ExpressionParser.parse("10.3", null, null));
		getNumeratorExerciseVariableMap.put("d", ExpressionParser.parse("-5", null, null));
		getNumeratorExerciseVariableMap.put("e", ExpressionParser.parse("5", null, null));
	}

	@Test
	public void testGetNumerator1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("getNumerator(3/7)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumerator2() throws EvaluatorException, OpenMathException {
		assertEquals(10, Evaluator.getNumberResult("getNumerator(10/7)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumerator3() throws EvaluatorException, OpenMathException {
		assertEquals(20.3, Evaluator.getNumberResult("getNumerator(20.3/-10)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumerator4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("getNumerator('0/5')", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumerator5() throws EvaluatorException, OpenMathException {
		assertNotEquals(4, Evaluator.getNumberResult("getNumerator('8/6')", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumerator6() throws EvaluatorException, OpenMathException {
		assertEquals(-5, Evaluator.getNumberResult("getNumerator(-5/3.2)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetNumeratorAtDefinitionZero() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getNumerator(3/0)", getNumeratorExerciseVariableMap, getNumeratorFillInVariableMap); // Result
																														// //
																														// undefined
	}

	@Test
	public void testGetNumeratorWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(20, Evaluator.getNumberResult("getNumerator([pos=2])", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("getNumerator([pos=1]/7)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(10.3, Evaluator.getNumberResult("getNumerator([pos=3]/5)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithInput4() throws EvaluatorException, OpenMathException {
		assertEquals(-5, Evaluator.getNumberResult("getNumerator([pos=4]/[pos=3])", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(20, Evaluator.getNumberResult("getNumerator([var=b])", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("getNumerator([var=a]/7)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(10.3, Evaluator.getNumberResult("getNumerator([var=c]/5)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithVariables4() throws EvaluatorException, OpenMathException {
		assertEquals(-5, Evaluator.getNumberResult("getNumerator([var=d]/[var=c])", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("getNumerator((2+3)/4)", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("getNumerator((getNumerator('3/2'))/4)",
				getNumeratorExerciseVariableMap, getNumeratorFillInVariableMap), 0.0);
	}

	@Test
	public void testGetNumeratorWithExpressions3() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("getNumerator((getNumerator('[pos=5]/7'))/4)",
				getNumeratorExerciseVariableMap, getNumeratorFillInVariableMap), 0.0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetNumeratorWithWrongInputPointNumber() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getNumerator(2.2)", getNumeratorExerciseVariableMap, getNumeratorFillInVariableMap);
		fail();
	}

	@Test(expected = ParserException.class)
	public void testGetNumeratorWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getNumerator(ab/2)", getNumeratorExerciseVariableMap, getNumeratorFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetNumeratorWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getNumerator('7/3', '12/2')", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetNumeratorWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getNumerator('1/1', '3/9', '7/8')", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testGetNumeratorWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getNumerator('[var=j]/3')", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testGetNumeratorWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getNumerator('[pos=42]/3')", getNumeratorExerciseVariableMap,
				getNumeratorFillInVariableMap);
		fail();
	}
}
