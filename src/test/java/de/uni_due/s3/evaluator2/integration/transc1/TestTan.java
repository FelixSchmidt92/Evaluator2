package de.uni_due.s3.evaluator2.integration.transc1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestTan extends TestIntegration {

	@BeforeClass
	public static void beforeTest() throws OpenMathException {
		fillInVariableMap.put(2, OMCreator.createOMOBJ(OMSymbol.NUMS1_PI));
	}

	@Test
	public void testTan1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('0')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTan2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('[var=PI]')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTan3() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('-[var=PI]')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTan4() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("tan('[var=PI]/4')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTan5() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("tan('-[var=PI]/4')", exerciseVariableMap, fillInVariableMap),
				0.0001);
	}

	@Test
	public void testTanWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('[pos=1]')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTanWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("tan('[pos=2]/4')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTanWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan('[var=a]')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTanWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("tan('[var=PI]/4')", exerciseVariableMap, fillInVariableMap), 0.0001); // Rounding
																															// Error
	}

	@Test
	public void testTanWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan(tan(0))", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTanWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("tan(tan(tan(0)))", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testTanWithONECharacter() throws EvaluatorException, OpenMathException {
		List<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMV("a"));
		assertEquals(OMCreator.createOMA(OMSymbol.TRANSC1_TAN, omel), Evaluator.evaluate("tan(a)", exerciseVariableMap, fillInVariableMap).getOMA());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testTanWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan('')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testTanWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan()", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testTanWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testTanWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("tan('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}