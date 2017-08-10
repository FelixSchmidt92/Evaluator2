package de.uni_due.s3.evaluator.core.integration.transc1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestArcSin extends TestIntegration {

	private double PI = Math.PI;

	@Test
	public void testAsin1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("asin(0)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAsin2() throws EvaluatorException, OpenMathException {
		assertEquals(-PI / 2, Evaluator.getNumberResult("asin(-1)", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAsin3() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("asin(1)", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAsin4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("asin('0')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAsin5() throws EvaluatorException, OpenMathException {
		assertEquals(0.775397496610753, Evaluator.getNumberResult("asin(0.7)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testAsinWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("asin([pos=1])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAsinWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("asin([pos=2])", exerciseVariableMap, fillInVariableMap),
				0.0001);
	}

	@Test
	public void testAsinWithInput3() throws EvaluatorException, OpenMathException {
		assertNotEquals(PI / 2, Evaluator.getNumberResult("asin([pos=3])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testAsinWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("asin([var=a])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAsinWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("asin([var=b])", exerciseVariableMap, fillInVariableMap),
				0.0001);
	}

	@Test
	public void testAsinWithVariables3() throws EvaluatorException, OpenMathException {
		assertNotEquals(PI / 2, Evaluator.getNumberResult("asin([var=c])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testAsinWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("asin(asin(asin(0)))", exerciseVariableMap, fillInVariableMap),
				0.0);

	}

	@Test
	public void testAsinWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(0.202744039431659,
				Evaluator.getNumberResult("asin(asin(0.2))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAsinWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("asin(a)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAsinWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("asin('')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testAsinWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("asin()", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testAsinWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("asin('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testAsinWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("asin('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}