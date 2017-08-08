package de.uni_due.s3.evaluator.core.integration.transc1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestArcCos extends TestIntegration {

	private double PI = Math.PI;

	@Test
	public void testAcos1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("acos('1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAcos2() throws EvaluatorException, OpenMathException {
		assertEquals(PI, Evaluator.getNumberResult("acos('-1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAcos3() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("acos('0')", exerciseVariableMap, fillInVariableMap), 0.000001);
	}

	@Test
	public void testAcos4() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("acos('0')", exerciseVariableMap, fillInVariableMap), 0.000001);
	}

	@Test
	public void testAcos5() throws EvaluatorException, OpenMathException {
		assertEquals(0.795398830184144, Evaluator.getNumberResult("acos(0.7)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testAcosWithInput1() throws EvaluatorException, OpenMathException {
		System.out.println(Evaluator.evaluate("acos([pos=1])",exerciseVariableMap,fillInVariableMap));
		assertEquals(PI / 2, Evaluator.getNumberResult("acos([pos=1])", exerciseVariableMap, fillInVariableMap),
				0.0001);
	}

	@Test
	public void testAcosWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("acos([pos=2])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAcosWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(PI, Evaluator.getNumberResult("acos([pos=3])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAcosWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("acos([var=a])", exerciseVariableMap, fillInVariableMap),
				0.000001);
	}

	@Test
	public void testAcosWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("acos([var=b])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAcosWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(PI, Evaluator.getNumberResult("acos([var=c])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAcosWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0.861722668365136,
				Evaluator.getNumberResult("acos(acos(acos(0.7)))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAcosWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("acos(acos(1))", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestAcosWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("acos(a)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAcosWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("acos('')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testAcosWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("acos()", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testAcosWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("acos('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testAcosWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("acos('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}