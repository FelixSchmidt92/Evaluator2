package de.uni_due.s3.evaluator.core.function.integration.arith1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.function.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestAbs extends TestIntegration {

	@Test
	public void testAbs1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("abs(1)", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbs2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("abs('-1')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbs3() throws EvaluatorException, OpenMathException {
		assertEquals(0.5, Evaluator.getNumberResult("abs(-0.5)", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbs4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("abs(-0)", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbsWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("abs('[pos=2]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbsWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("abs('[pos=1]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbsWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("abs('[var=b]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbsWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("abs('[var=a]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbsWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("abs(-abs(5))", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAbsWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("abs(-abs(abs(-5)))", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test (expected = ParserException.class)
	public void testAbsWithWrongInputCharacter1() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("abs(a)", exerciseVariableMap, fillInVariableMap);
	}

	@Test (expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testAbsWithTwoArguments1() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("abs(-2.2, 3.3)", exerciseVariableMap, fillInVariableMap);
	}

	@Test (expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testAbsWithThreeArguments1() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("abs(2.123, -3.55, -1)", exerciseVariableMap, fillInVariableMap);
	}

	@Test (expected = UndefinedExerciseVariableException.class)
	public void testAbsWithMissingExcerciseVariable1() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("abs('[var=j]')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testAbsWithMissingInput1() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("abs('[pos=42]')", exerciseVariableMap, fillInVariableMap);
	}

}
