package de.uni_due.s3.evaluator2.core.integration.openmath_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCountNodes extends TestIntegration {

	@Test 
	public void testCountNodes1() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("countNodes(2+2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test 
	public void testCountNodes2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("countNodes(3*(2+2))", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test 
	public void testCountNodes3() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("countNodes(2+2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test 
	public void testCountNodesWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("countNodes([pos=2])", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test 
	public void testCountNodesWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("countNodes(3*([pos=1]+2))", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test 
	public void testCountNodesWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("countNodes([var=a])", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test 
	public void testCountNodesWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("countNodes(3*([var=a]+2))", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test 
	public void testCountNodesWithExpressions() throws EvaluatorException, OpenMathException {
		assertEquals(6, Evaluator.getNumberResult("countNodes('countNodes(2+2)')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test (expected=ParserException.class)
	public void testCountNodesWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countNodes(ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testCountNodesWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countNodes(3, 2)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testCountNodesWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countNodes(1, 2, 3)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testCountNodesWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countNodes('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testCountNodesWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("countNodes('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
