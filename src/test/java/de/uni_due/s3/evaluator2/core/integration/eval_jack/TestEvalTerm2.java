package de.uni_due.s3.evaluator2.core.integration.eval_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvalTerm2 extends TestIntegration {

	@Test
	public void testEvalTermIn2Variables1() throws EvaluatorException, OpenMathException {
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
	public void testEvalTermIn2VariablesWithCharacterAsSecondAndThirdArgument1()
			throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMV("b"));
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.ARITH1_TIMES, omel)),
				Evaluator.evaluate("evalterm2('a+b','b', 'a')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testEvalTermIn2VariablesWithCharacterAsSecondAndThirdArgument2()
			throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMV("c"));
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.ARITH1_TIMES, omel)),
				Evaluator.evaluate("evalterm2('a+b','c', 'a')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testEvalTermIn2VariablesWithONECharacter() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMV("a")),
				Evaluator.evaluate("evalterm2(a, a, a)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
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
