package de.uni_due.s3.evaluator.core.integration.eval_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvalTerm2 extends TestIntegration {

	@Test
	public void testEvalTermIn2Variables1() throws EvaluatorException, OpenMathException {
		System.out
				.println(Evaluator.getNumberResult("evalterm2('x+y','1','2')", exerciseVariableMap, fillInVariableMap));
		assertEquals(3, Evaluator.getNumberResult("evalterm2('x+y','1','2')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testEvalTermIn2Variables2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("evalterm2('3*x+2*y','1','2')", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testEvalTermIn2Variables3() throws EvaluatorException, OpenMathException {
		assertNotEquals(7,
				Evaluator.getNumberResult("evalterm2('3*x+2*y','2','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalTermIn2Variables4() throws EvaluatorException, OpenMathException {
		assertEquals(1,
				Evaluator.getNumberResult("evalterm2('2*a+6*b-3','2','0')", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalTermIn2VariablesWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(30, Evaluator.getNumberResult("evalterm2('6*x+27*y+3','[pos=1]','[pos=2]')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalTermIn2VariablesWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("evalterm2('2*a+6*b-3','[pos=1]','[pos=2]')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}
	

	@Test
	public void testEvalTermIn2VariablesWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("evalterm2('[var=a]*x+[var=b]*y','2','1')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalTermIn2VariablesWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("evalterm2('[var=a]*x+[var=b]*y','0','1')", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testEvalTermIn2VariablesWithCharacterAsSecondAndThirdArgument()
			throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("2*a")),
				Evaluator.evaluate("evalterm2('a+b','b', 'a')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalTermIn2VariablesWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")),
				Evaluator.evaluate("evalterm2(a, a, a)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEvalTermIn2VariablesWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalterm2('', '', '')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalTermIn2VariablesWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalterm2()", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testEvalTermIn2VariablesWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalterm2([var=j], '1', '2')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testEvalTermIn2VariablesWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("evalterm2([pos=42], '1', '2')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

}
