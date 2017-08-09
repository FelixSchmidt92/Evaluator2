package de.uni_due.s3.evaluator.core.integration.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIntegrate extends TestIntegration {

	HashMap<Integer, OMOBJ> integrateFillInVariableMap = new HashMap<>();
	HashMap<String, OMOBJ> integrateExerciseVariableMap = new HashMap<>();

	@Before
	public void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		integrateFillInVariableMap.put(1, ExpressionParser.parse("2", null, null));
		integrateFillInVariableMap.put(2, ExpressionParser.parse("1", null, null));

		integrateExerciseVariableMap.put("a", ExpressionParser.parse("2", null, null));
		integrateExerciseVariableMap.put("b", ExpressionParser.parse("1", null, null));
	}

	@Test
	public void testIntegrate1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x", null, null), Evaluator.evaluate(
				"integrate('2','x')", integrateExerciseVariableMap, integrateFillInVariableMap));
	}

	@Test
	public void testIntegrate2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*y", null, null), Evaluator.evaluate(
				"integrate('2','y')", integrateExerciseVariableMap, integrateFillInVariableMap));
	}

	@Test
	public void testIntegrateWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x", null, null), Evaluator.evaluate(
				"integrate('[pos=1]','x')", integrateExerciseVariableMap, integrateFillInVariableMap));
	}

	@Test
	public void testIntegrateWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("y", null, null), Evaluator.evaluate(
				"integrate('[pos=2]','y')", integrateExerciseVariableMap, integrateFillInVariableMap));
	}

	@Test
	public void testIntegrateWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("2*x", null, null), Evaluator.evaluate(
				"integrate('[var=a]','x')", integrateExerciseVariableMap, integrateFillInVariableMap));
	}

	@Test
	public void testIntegrateWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("y", null, null), Evaluator.evaluate(
				"integrate('[var=b]','y')", integrateExerciseVariableMap, integrateFillInVariableMap));
	}

	@Test
	public void testIntegrateWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("(a^2)/2", null, null), Evaluator.evaluate(
				"integrate(a, a)", integrateExerciseVariableMap, integrateFillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIntegrateWithEmptyStringArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("integrate('','')", integrateExerciseVariableMap,
				integrateFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIntegrateWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("integrate()", integrateExerciseVariableMap, integrateFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIntegrateWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("integrate('y', '[var=j]')", integrateExerciseVariableMap,
				integrateFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIntegrateWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("integrate('y', '[pos=42]')", integrateExerciseVariableMap,
				integrateFillInVariableMap);
		fail();
	}
}
