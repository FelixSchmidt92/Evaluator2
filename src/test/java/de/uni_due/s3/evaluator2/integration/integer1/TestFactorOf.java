package de.uni_due.s3.evaluator2.integration.integer1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestFactorOf extends TestIntegration {

	@Test
	public void testFactorOf1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("factorOf('2*a+c','c','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOf2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("factorOf('2*x+x-3*x','x','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOf3() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("factorOf('2*y^2+3*y','y','2')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOf4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("factorOf('5*y^2+3*y','y','0')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOfWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("factorOf('2*a+[pos=2]*c','c','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOfWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("factorOf('2*y^2+[pos=1]*y','y','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOfWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("factorOf('2*a+[var=b]*c','c','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOfWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("factorOf('2*y^2+[var=a]*y','y','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOfWithPointNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals(3.3, Evaluator.getNumberResult("factorOf('2*a+3.3*c','c','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testFactorOfWithPointNumbers2() throws EvaluatorException, OpenMathException {
		assertEquals(-8.1, Evaluator.getNumberResult("factorOf('2*y^2+(-8.1)*y','y','1')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test(expected=ParserException.class)
	public void testFactorOfWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("factorOf(ab, ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testFactorOfWithEmptyStringArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("factorOf('', '', '')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testFactorOfWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("factorOf()", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testFactorOfWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("factorOf('2y^2')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testFactorOfWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("factorOf('2y^2', 'y')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testFactorOfWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("factorOf('2y^2', 'y', '[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testFactorOfWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("factorOf('2y^2', 'y', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
