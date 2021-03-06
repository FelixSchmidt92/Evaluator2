package de.uni_due.s3.evaluator2.integration.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
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
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIntegrate extends TestIntegration {

	static HashMap<Integer, OMOBJ> integrateFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> integrateExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException, ErroneousExerciseVariableException, OpenMathException {
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
		ArrayList<Object> rational = new ArrayList<>();
		rational.add(OMCreator.createOMI(1));
		rational.add(OMCreator.createOMI(2));
		
		ArrayList<Object> power = new ArrayList<>();
		power.add(OMCreator.createOMV("a"));
		power.add(OMCreator.createOMI(2));
		
		ArrayList<Object> expected = new ArrayList<>();
		expected.add(OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, rational));
		expected.add(OMCreator.createOMA(OMSymbol.ARITH1_POWER, power));
		
		OMOBJ expect = OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.ARITH1_TIMES, expected));
		//Creating OMOBJ (1/2)*(a^2)  --> This is also a correct answer!
		
		assertEquals(expect, Evaluator.evaluate(
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
