package de.uni_due.s3.evaluator.core.integration.transc1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestArcTan extends TestIntegration {
	
	private double PI = Math.PI;
	
	@Test
	public void testAtan1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan('0')", exerciseVariableMap, fillInVariableMap),0.0001);	
	}
	
	@Test
	public void testAtan2() throws EvaluatorException, OpenMathException {
		assertEquals(0.4636476090008061, Evaluator.getNumberResult("atan('0.5')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan4() throws EvaluatorException, OpenMathException {
		assertEquals(PI/4, Evaluator.getNumberResult("atan('1')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan5() throws EvaluatorException, OpenMathException {
		assertEquals(-PI/4, Evaluator.getNumberResult("atan('-1')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan6() throws EvaluatorException, OpenMathException {
		assertEquals(PI/4, Evaluator.getNumberResult("atan(1)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan7() throws EvaluatorException, OpenMathException {
		assertEquals(-PI/4, Evaluator.getNumberResult("atan(-1)", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAtanWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan([pos=1])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtanWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(PI/4, Evaluator.getNumberResult("atan([pos=2])", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAtanWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan([var=a])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtanWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(PI/4, Evaluator.getNumberResult("atan([var=b])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtanWithExpression1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan(atan(0))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtanWithExpression2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan(atan(atan(0)))", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test(expected=ParserException.class)
	public void testAtanWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan(a)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testAtanWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan('')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testAtanWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan()", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testAtanWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan([var=j])", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testAtanWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan([pos=42])", exerciseVariableMap, fillInVariableMap);
	}
}