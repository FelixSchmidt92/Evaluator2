package de.uni_due.s3.evaluator.core.integration.transc1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSin extends TestIntegration {
	
	@Before
	public void beforeTest() throws OpenMathException {
		fillInVariableMap.put(2, OMCreator.createOMOBJ(OMSymbol.NUMS1_PI));
	}
	
	@Test
	public void testSin1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("sin('0')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSin2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("sin('Pi/2')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSin3() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("sin('-Pi/2')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSin4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("sin('Pi')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSin5() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("sin('-Pi')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSinWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("sin('[pos=1]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSinWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("sin('[pos=2]/2')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testSinWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("sin('[var=a]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSinWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("sin('[var=PI]/2')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSinWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("sin(sin(0))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testSinWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("sin(sin(sin(0)))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test(expected=ParserException.class)
	public void testSinWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("sin(a)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testSinWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("sin('')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testSinWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("sin()", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testSinWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("sin('[var=j]')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testSinWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("sin('[pos=42]')", exerciseVariableMap, fillInVariableMap);
	}
}