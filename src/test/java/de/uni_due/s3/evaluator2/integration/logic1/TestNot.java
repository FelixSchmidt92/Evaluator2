package de.uni_due.s3.evaluator2.integration.logic1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestNot extends TestIntegration {

	@Test
	public void testBooleanNot1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("!0", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testBooleanNot2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("!1", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testBooleanNot3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("!(3+4==6)", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testBooleanNotWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("![pos=1]", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testBooleanNotWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("![pos=2]", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testBooleanNotWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("![var=a]", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testBooleanNotWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("![var=b]", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testBooleanNotWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("!!0", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testBooleanNotWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("!!!0", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testBooleanNotWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("!'a'", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
