package de.uni_due.s3.evaluator.core.function.integration.transc2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.function.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestArcTan2 extends TestIntegration {
	
	private double PI = Math.PI;
	
	@Test
	public void testAtan2_1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan2('0','0')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2_2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan2('0','0.5')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2_3() throws EvaluatorException, OpenMathException {
		assertEquals(0.7853981633974483, Evaluator.getNumberResult("atan2('0.5','0.5')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2AtDefinition1() throws EvaluatorException, OpenMathException {
		assertEquals(PI, Evaluator.getNumberResult("atan2(0, -1)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2AtDefinition2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan2(0, 1)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2AtDefinition3() throws EvaluatorException, OpenMathException {
		assertEquals(PI/2, Evaluator.getNumberResult("atan2(1, 0)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2AtDefinition4() throws EvaluatorException, OpenMathException {
		assertEquals(-PI/2, Evaluator.getNumberResult("atan2(-1, 0)", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAtan2WithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan2('0','[pos=1]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2WithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(PI/2, Evaluator.getNumberResult("atan2('[pos=2]','[pos=1]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test
	public void testAtan2WithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan2('[var=a]','0')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2WithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(PI/2, Evaluator.getNumberResult("atan2('[var=b]','[var=a]')", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2WithExpression1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan2(atan2(0, 0), atan2(0, 0))", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test
	public void testAtan2WithExpression2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("atan2(atan2(0, 0), atan2(0, atan2(0, 0)))", exerciseVariableMap, fillInVariableMap),0.0001);
	}

	@Test(expected=ParserException.class)
	public void testAtan2WithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan2(a, a)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testAtan2WithEmptyStringArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan2('', '')", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testAtan2WithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan2()", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testAtan2WithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan2([var=j], [var=j])", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testAtan2WithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("atan2([pos=42], [pos=42])", exerciseVariableMap, fillInVariableMap);
	}
}