package de.uni_due.s3.evaluator.core.integration.rounding_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRint extends TestIntegration{

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	
	@BeforeClass
	public static void beforeTest() {
		OMOBJ obj1 = new OMOBJ();
		OMOBJ obj2 = new OMOBJ();
		OMOBJ obj3 = new OMOBJ();
		OMOBJ obj4 = new OMOBJ();
		obj1.setOMF(OMCreator.createOMF(50.33333));
		obj2.setOMF(OMCreator.createOMF(99.993444));
		obj3.setOMF(OMCreator.createOMF(5.49999));
		obj4.setOMF(OMCreator.createOMF(1233.511111));
		
		fillIn.put(5, obj3);
		fillIn.put(1234, obj4);
		
		exerVar.put("f1", obj1);
		exerVar.put("h", obj2);
	}
	
	@Test 
	public void testRint1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(12  , Evaluator.getNumberResult("rint(12.4999)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testRint2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(13  , Evaluator.getNumberResult("rint(12.500001)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testRint3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(12  , Evaluator.getNumberResult("rint(12.5)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testRint4() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(14  , Evaluator.getNumberResult("rint(13.5)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testRint5() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(-99 , Evaluator.getNumberResult("rint(-99.4999)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testRint6() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(-100, Evaluator.getNumberResult("rint(-99.5999)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testRint7() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(0 , Evaluator.getNumberResult("rint(-0.00001)", exerVar, fillIn), 0);
	}
	
	@Test 
	public void testRint8() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(0 , Evaluator.getNumberResult("rint(0.00001)", exerVar, fillIn), 0);	
	}
	
	@Test
	public void testRintWithInput1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(5 , Evaluator.getNumberResult("rint([pos=5])", exerVar, fillIn), 0);
	}
	
	@Test
	public void testRintWithInput2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(1234 , Evaluator.getNumberResult("rint([pos=1234])", exerVar, fillIn), 0);
	}
	
	@Test
	public void testRintWithVariables1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(50 , Evaluator.getNumberResult("rint([var=f1])", exerVar, fillIn), 0);
	}
	
	@Test
	public void testRintWithVariables2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(100 ,Evaluator.getNumberResult("rint([var=h])", exerVar, fillIn), 0);
	}
	
	@Test
	public void testRintWithPointNumbers1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(0 , Evaluator.getNumberResult("rint(0.2)", exerVar, fillIn), 0);	
		
	}
	
	@Test
	public void testRintWithPointNumbers2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(0 , Evaluator.getNumberResult("rint(-0.2)", exerVar, fillIn), 0);	
	}
	
	@Test
	public void testRintWithExpressions1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(-2 , Evaluator.getNumberResult("rint(rint(-2.2))", exerVar, fillIn), 0);
	}
	
	@Test
	public void testRintWithExpressions2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(2 , Evaluator.getNumberResult("rint(rint(rint(2.1)))", exerVar, fillIn), 0);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testRintWithWrongInputCharacter() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("rint(a)", exerVar, fillIn);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testRintWithTwoArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("rint(4.2, 3.1)", exerVar, fillIn);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testRintWithThreeArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("rint(2.1, 2.2, 2.3)", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testRintWithMissingExerciseVariable() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("rint('[var=j]')", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testRintWithMissingInput() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("rint('[pos=42]')", exerVar, fillIn);
	}
}