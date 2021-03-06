package de.uni_due.s3.evaluator2.integration.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestNumberOfVariables extends TestIntegration {

	static HashMap<Integer, OMOBJ> numberOfVariablesFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> numberOfVariablesExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException, ErroneousExerciseVariableException, OpenMathException {
		numberOfVariablesFillInVariableMap.put(1, ExpressionParser.parse("0", null, null));
		numberOfVariablesFillInVariableMap.put(2, ExpressionParser.parse("3", null, null));
		numberOfVariablesFillInVariableMap.put(3, ExpressionParser.parse("x", null, null));

		numberOfVariablesExerciseVariableMap.put("a", ExpressionParser.parse("x", null, null));
		numberOfVariablesExerciseVariableMap.put("b", ExpressionParser.parse("3", null, null));
	}

	@Test
	public void testNumberOfVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("numberOfVariables('2x+3a-x+2b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("numberOfVariables('2x+3x-x+2x')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("numberOfVariables('sin(i)*conste()^(e)+ q/root(s, 2)')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariables4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("numberOfVariables('5')", numberOfVariablesExerciseVariableMap,
				numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("numberOfVariables('2*x+3*a-x+2*b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("numberOfVariables('2*[pos=3]+3*a-[pos=3]+2*b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("numberOfVariables('2*[pos=3]+3*a-[pos=3]+2*b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithInput4() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("numberOfVariables('2*[pos=3]+3*[pos=3]-[pos=3]+2*b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("numberOfVariables('2x+3a-x+2b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("numberOfVariables('2[var=a]+3a-[var=a]+2b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("numberOfVariables('2[var=a]+3a-[var=a]+2b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithVariables4() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("numberOfVariables('2[var=a]+3[var=a]-[var=a]+2b')",
				numberOfVariablesExerciseVariableMap, numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("numberOfVariables(a)", numberOfVariablesExerciseVariableMap,
				numberOfVariablesFillInVariableMap), 0.0);
	}

	@Test
	public void testNumberOfVariablesWithEmptyStringArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("numberOfVariables('')", numberOfVariablesExerciseVariableMap,
				numberOfVariablesFillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testNumberOfVariablesWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("numberOfVariables()", numberOfVariablesExerciseVariableMap,
				numberOfVariablesFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testNumberOfVariablesWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("numberOfVariables('[var=j]')", numberOfVariablesExerciseVariableMap,
				numberOfVariablesFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testNumberOfVariablesWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("numberOfVariables('[pos=42]')", numberOfVariablesExerciseVariableMap,
				numberOfVariablesFillInVariableMap);
		fail();
	}
}
