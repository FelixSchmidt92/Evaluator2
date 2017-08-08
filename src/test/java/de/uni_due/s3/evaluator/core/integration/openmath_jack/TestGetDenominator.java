package de.uni_due.s3.evaluator.core.integration.openmath_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetDenominator extends TestIntegration {

	HashMap<Integer, OMOBJ> getDenominatorFillInVariableMap = new HashMap<>();
	HashMap<String, OMOBJ> getDenominatorExerciseVariableMap = new HashMap<>();

	@Before
	public void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		getDenominatorFillInVariableMap.put(1, ExpressionParser.parse("0", null, null));
		getDenominatorFillInVariableMap.put(2, ExpressionParser.parse("20/3", null, null));
		getDenominatorFillInVariableMap.put(3, ExpressionParser.parse("10.3", null, null));
		getDenominatorFillInVariableMap.put(4, ExpressionParser.parse("-5", null, null));
		getDenominatorFillInVariableMap.put(5, ExpressionParser.parse("5", null, null));

		getDenominatorExerciseVariableMap.put("a", ExpressionParser.parse("0", null, null));
		getDenominatorExerciseVariableMap.put("b", ExpressionParser.parse("20/3", null, null));
		getDenominatorExerciseVariableMap.put("c", ExpressionParser.parse("10.3", null, null));
		getDenominatorExerciseVariableMap.put("d", ExpressionParser.parse("-5", null, null));
		getDenominatorExerciseVariableMap.put("e", ExpressionParser.parse("5", null, null));
	}

	@Test
	public void testGetDenominator1() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("getDenominator(3/7)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testGetDenominator2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("getDenominator(10/7)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testGetDenominator3() throws EvaluatorException, OpenMathException {
		assertEquals(-10, Evaluator.getNumberResult("getDenominator(20/-10)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominator4() throws EvaluatorException, OpenMathException {
		assertEquals(3.2, Evaluator.getNumberResult("getDenominator('-5/3.2')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominator5() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("getDenominator('0/5')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominator6() throws EvaluatorException, OpenMathException {
		assertNotEquals(3, Evaluator.getNumberResult("getDenominator('-5/6')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominator7() throws EvaluatorException, OpenMathException {
		assertNotEquals(2, Evaluator.getNumberResult("getDenominator('8/4')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetDenominatorAtDefinitionZero() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator(3/0)", exerciseVariableMap, fillInVariableMap); // Result of 3/0 is
																									// undefined
	}

	@Test
	public void testGetDenominatorWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("getDenominator([pos=2])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("getDenominator([pos=1]/7)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(10.3,
				Evaluator.getNumberResult("getDenominator(5/[pos=3])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testGetDenominatorWithInput4() throws EvaluatorException, OpenMathException {
		assertEquals(5,
				Evaluator.getNumberResult("getDenominator([pos=3]/[pos=5])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithInput5() throws EvaluatorException, OpenMathException {
		assertEquals(-5,
				Evaluator.getNumberResult("getDenominator([pos=3]/[pos=4])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("getDenominator([var=b])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("getDenominator([var=a]/7)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(10.3,
				Evaluator.getNumberResult("getDenominator(5/[var=c])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testGetDenominatorWithVariables4() throws EvaluatorException, OpenMathException {
		assertEquals(5,
				Evaluator.getNumberResult("getDenominator([var=c]/[var=e])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithVariables5() throws EvaluatorException, OpenMathException {
		assertEquals(-5,
				Evaluator.getNumberResult("getDenominator([var=c]/[var=d])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testGetDenominatorWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("getDenominator('4/(getDenominator('3/2'))')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testGetDenominatorWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("getDenominator('4/(getDenominator('3/[pos=5]'))')",
				exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetDenominatorWithWrongInputPointNumber() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator('7.2')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetDenominatorWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator('ab/3.3')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetDenominatorWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator('7/3', '12/2')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetDenominatorWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator('1/1', '3/9', '7/8')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testGetDenominatorWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator('[var=j]/3')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testGetDenominatorWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getDenominator('[pos=42]/3')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
