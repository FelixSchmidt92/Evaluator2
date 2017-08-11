package de.uni_due.s3.evaluator2.core.integration.rounding1;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCeil extends TestIntegration {
	
	
	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	
	@BeforeClass
	public static void beforeTest() {
		OMOBJ fourFive = new OMOBJ();
		fourFive.setOMF(OMCreator.createOMF(4.5));
		
		fillIn.put(1, fourFive);
		
		exerVar.put("a", fourFive);
	}
	
	@Test 
	public void testCeil1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(1 , Evaluator.getNumberResult("ceil('0.5')", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeil2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(-3 , Evaluator.getNumberResult("ceil(-3.000001)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeil3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(1 , Evaluator.getNumberResult("ceil(1)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeil4() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(0 , Evaluator.getNumberResult("ceil(0)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeilWithInput1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(1 , Evaluator.getNumberResult("ceil('0.5')", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeilWithInput2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(5 , Evaluator.getNumberResult("ceil('[pos=1]')", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeilWithVariables1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(1 , Evaluator.getNumberResult("ceil('0.5')", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeilWithVariables2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(5 , Evaluator.getNumberResult("ceil('[var=a]')", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testCeilWithExpressions1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(-2 , Evaluator.getNumberResult("ceil(ceil(-2.2))", exerVar, fillIn), 0);
		
	}
	
	@Test 
	public void testCeilWithExpressions2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		assertEquals(2 , Evaluator.getNumberResult("ceil(ceil(ceil(1.11)))", exerVar, fillIn), 0);
	}
	
	@Test (expected=FunctionInvalidArgumentTypeException.class)//(expected=InvalidEvaluatorFunctionArgumentException.class)
	public void testCeilWithWrongInputCharacter() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.getNumberResult("ceil(a)", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testCeilWithTwoArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.getNumberResult("ceil(2.2, 3.3)", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testCeilWithThreeArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.getNumberResult("ceil(2.2, 3.3, -1.1)", exerVar, fillIn);
	}

	@Test (expected=UndefinedExerciseVariableException.class)
	public void testCeilWithMissingExcerciseVariable() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.getNumberResult("ceil('[var=j]')", exerVar, fillIn);
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testCeilWithMissingInput() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.getNumberResult("ceil('[pos=42]')", exerVar, fillIn);
	}

}
