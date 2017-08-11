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

public class TestFloor extends TestIntegration{

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	
	@BeforeClass
	public static void beforeTest() {
		OMOBJ zero = new OMOBJ();
		OMOBJ twoThree = new OMOBJ();
		OMOBJ mFourOne = new OMOBJ();
		zero.setOMI(OMCreator.createOMI(0));
		twoThree.setOMF(OMCreator.createOMF(2.3));
		mFourOne.setOMF(OMCreator.createOMF(-4.156665));
		
		fillIn.put(1, zero);
		fillIn.put(2, twoThree);
		fillIn.put(3, mFourOne);
		
		exerVar.put("a", zero);
		exerVar.put("b", twoThree);
		exerVar.put("c", mFourOne);
	}
	
	@Test
	public void testFloor1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 5 , Evaluator.getNumberResult("floor(5.4)", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloor2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 5 , Evaluator.getNumberResult("floor(5.0)", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloor3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 0 , Evaluator.getNumberResult("floor(0)", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloor4() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(-6 , Evaluator.getNumberResult("floor(-5.4343664)", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloorWithInput1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 0 , Evaluator.getNumberResult("floor([pos=1])", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloorWithInput2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 2 , Evaluator.getNumberResult("floor([pos=2])", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloorWithInput3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(-5 , Evaluator.getNumberResult("floor([pos=3])", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloorWithVariables1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 0 , Evaluator.getNumberResult("floor([var=a])", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloorWithVariables2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 2 , Evaluator.getNumberResult("floor([var=b])", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloorWithVariables3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(-5 , Evaluator.getNumberResult("floor([var=c])", exerVar, fillIn), 0 );
	}
	
	@Test
	public void testFloorWithExpressions() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals( 5 , Evaluator.getNumberResult("floor(floor(5.1354))", exerVar, fillIn), 0 );
	}

	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testFloorWithWrongInputCharacter() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("floor(a)", exerVar, fillIn);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testFloorWithTwoArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("floor(7.4321, 3)", exerVar, fillIn);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testFloorWithThreeArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("floor(1.434321, 5, 7)", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testFloorWithMissingExerciseVariable() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("floor([var=j])", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testFloorWithMissingInput() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("floor([pos=42])", exerVar, fillIn);
	}
}