package de.uni_due.s3.evaluator.core.integration.eval_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEval extends TestIntegration {

	@Test
	public void testEval1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("eval('1+1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEval2() throws EvaluatorException, OpenMathException {
		assertEquals(2.2, Evaluator.getNumberResult("eval('1.2+1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEval3() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("eval(|-5|)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEval4() throws EvaluatorException, OpenMathException {
		assertEquals(12, Evaluator.getNumberResult("eval('2+5*2')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEval5() throws EvaluatorException, OpenMathException {
		assertEquals(16, Evaluator.getNumberResult("eval('2*(5+3)')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEval6() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("eval(5%2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEval7() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("eval('2+2')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("eval('[pos=1]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("eval('[pos=2]-1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("eval('[var=a]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("eval('[var=b]-1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(6, Evaluator.getNumberResult("eval(eval('2+2') + eval('1+1'))", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("eval(eval(1))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalWithFunction1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("5x")),
				Evaluator.evaluate("eval('2x+3x')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalWithFunction2() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("eval('2x=2')", exerciseVariableMap, fillInVariableMap),0.0);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testEvalWithWrongInputEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("eval('')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testEvalWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("eval('a')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("eval(0, 1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("eval(1, 0, 0)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testEvalWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("eval('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testEvalWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("eval('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
