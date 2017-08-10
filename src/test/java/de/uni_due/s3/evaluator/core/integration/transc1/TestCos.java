package de.uni_due.s3.evaluator.core.integration.transc1;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCos extends TestIntegration {

	@BeforeClass
	public static void beforeTest() throws OpenMathException {
		fillInVariableMap.put(3, OMCreator.createOMOBJ(OMSymbol.NUMS1_PI));

		exerciseVariableMap.put("c", OMCreator.createOMOBJ(OMSymbol.NUMS1_PI));
	}

	@Test
	public void testCos1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("cos('0')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCos2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("cos('[var=PI]/2')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testCos3() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("cos('[var=PI]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCos4() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("cos(0)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCos5() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("cos([var=PI]/2)", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testCos6() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("cos([var=PI])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCosWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("cos('[pos=1]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCosWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0.5403023058681398,
				Evaluator.getNumberResult("cos('[pos=2]')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testCosWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("cos('[pos=3]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCosWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("cos('[var=a]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCosWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0.5403023058681398,
				Evaluator.getNumberResult("cos('[var=b]')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testCosWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("cos('[var=c]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCosWithVariables4() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("cos('[var=PI]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testCosWithExpression1() throws EvaluatorException, OpenMathException {
		assertEquals(0.5403023058681398,
				Evaluator.getNumberResult("cos(cos(0))", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testCosWithExpression2() throws EvaluatorException, OpenMathException {
		assertEquals(0.8575532158463934,
				Evaluator.getNumberResult("cos(cos(cos(0)))", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testCosWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("cos(a)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testCosWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("cos('')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCosWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("cos()", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testCosWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("cos([var=j])", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testCosWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("cos([pos=42])", exerciseVariableMap, fillInVariableMap);
	}
}