package de.uni_due.s3.evaluator2.core.integration.transc1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestArcTan extends TestIntegration {

	private double PI = Math.PI;

	@Test
	public void testAtan1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan('0')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAtan2() throws EvaluatorException, OpenMathException {
		assertEquals(0.463647609000806,
				Evaluator.getNumberResult("atan('0.5')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAtan4() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 4, Evaluator.getNumberResult("atan('1')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAtan5() throws EvaluatorException, OpenMathException {
		assertEquals(-PI / 4, Evaluator.getNumberResult("atan('-1')", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAtan6() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 4, Evaluator.getNumberResult("atan(1)", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAtan7() throws EvaluatorException, OpenMathException {
		assertEquals(-PI / 4, Evaluator.getNumberResult("atan(-1)", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAtanWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan([pos=1])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAtanWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 4, Evaluator.getNumberResult("atan([pos=2])", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAtanWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan([var=a])", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAtanWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 4, Evaluator.getNumberResult("atan([var=b])", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testAtanWithExpression1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan(atan(0))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAtanWithExpression2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan(atan(atan(0)))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAtanWithONECharacter() throws EvaluatorException, OpenMathException {
		List<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMV("a"));
		assertEquals(OMCreator.createOMA(OMSymbol.TRANSC1_ARCTAN, omel), Evaluator.evaluate("atan(a)", exerciseVariableMap, fillInVariableMap).getOMA());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAtanWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan('')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testAtanWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan()", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testAtanWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan([var=j])", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testAtanWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan([pos=42])", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}