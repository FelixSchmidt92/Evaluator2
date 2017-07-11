package de.uni_due.s3.evaluator.core.function.integration.arith1;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
		assertTrue(1 == getNumberResult("abs(1)"));
	}

	@Test
	public void testAbs2() throws EvaluatorException, OpenMathException {
		assertTrue(1 == getNumberResult("abs('-1')"));
	}

	@Test
	public void testAbs3() throws EvaluatorException, OpenMathException {
		assertTrue(0.5 == getNumberResult("abs(-0.5)"));
	}

	@Test
	public void testAbs4() throws EvaluatorException, OpenMathException {
		assertTrue(0 == getNumberResult("abs(-0)"));
	}

	@Test
	public void testAbsWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(1 == getNumberResult("abs('[pos=2]')"));
	}

	@Test
	public void testAbsWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(0 == getNumberResult("abs('[pos=1]')"));
	}

	@Test
	public void testAbsWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(1 == getNumberResult("abs('[var=b]')"));
	}

	@Test
	public void testAbsWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(0 == getNumberResult("abs('[var=a]')"));
	}

	@Test
	public void testAbsWithExpressions1() throws EvaluatorException, OpenMathException {
		assertTrue(5 == getNumberResult("abs(-abs(5))"));
	}

	@Test
	public void testAbsWithExpressions2() throws EvaluatorException, OpenMathException {
		assertTrue(5 == getNumberResult("abs(-abs(abs(-5)))"));
	}

	@Test (expected = ParserException.class)
	public void testAbsWithWrongInputCharacter1() throws EvaluatorException, OpenMathException {
		getNumberResult("abs(a)");
	}

	@Test (expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testAbsWithTwoArguments1() throws EvaluatorException, OpenMathException {
		getNumberResult("abs(-2.2, 3.3)");
	}

	@Test (expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testAbsWithThreeArguments1() throws EvaluatorException, OpenMathException {
		getNumberResult("abs(2.123, -3.55, -1)");
	}

	@Test (expected = UndefinedExerciseVariableException.class)
	public void testAbsWithMissingExcerciseVariable1() throws EvaluatorException, OpenMathException {
		getNumberResult("abs('[var=j]')");
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testAbsWithMissingInput1() throws EvaluatorException, OpenMathException {
		getNumberResult("abs('[pos=42]')");
	}

}
