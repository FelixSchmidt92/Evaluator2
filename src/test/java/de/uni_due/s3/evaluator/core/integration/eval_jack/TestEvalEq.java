package de.uni_due.s3.evaluator.core.integration.eval_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvalEq extends TestIntegration {

	@Test
	public void testEvalEq1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("-1-x+x^2")),
				Evaluator.evaluate("evalEq('x^2', 'x+1')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEq2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("x^2-(y+1)")),
				Evaluator.evaluate("evalEq('x^2', 'y+1')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEq3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("5-(5)")),
				Evaluator.evaluate("evalEq('5', '5')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("-4-x^2")),
				Evaluator.evaluate("evalEq('[pos=1]','x^2+4')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("-3+3*x")),
				Evaluator.evaluate("evalEq('3x-2','[pos=2]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("-4-x^2")),
				Evaluator.evaluate("evalEq('[var=a]','x^2+4')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("-3+3*x")),
				Evaluator.evaluate("evalEq('3x-2','[var=b]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalEqWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a-(a)")),
				Evaluator.evaluate("evalEq(a, a)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testEvalEqWithEmptyArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalEq('' , '')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testEvalEqWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalEq([var=j], [var=j])", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testEvalEqWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalEq([pos=42], [pos=42])", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
