package de.uni_due.s3.evaluator.core.integration.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsNumber extends TestIntegration{

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	
	@BeforeClass
	public static void beforeTest(){
		OMOBJ zero = new OMOBJ();
		OMOBJ mTwelve = new OMOBJ();
		OMOBJ twoHundred = new OMOBJ();
		zero.setOMI(OMCreator.createOMI(0));
		mTwelve.setOMI(OMCreator.createOMI(-12));
		twoHundred.setOMI(OMCreator.createOMI(200));
		
		exerVar.put("a", twoHundred);
		exerVar.put("b", zero);
		exerVar.put("c", mTwelve);
		
		fillIn.put(1, twoHundred);
		fillIn.put(2, zero);
		fillIn.put(3, mTwelve);
	}
	
	@Test
	public void testIsNumber1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('10')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumber2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('1234567890')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumber3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('-101230')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumber4() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(false, Evaluator.getBooleanResult("isNumber('12$56&')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumber5() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(false, Evaluator.getBooleanResult("isNumber('&!§$%&/()123asdf')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumber6() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(false, Evaluator.getBooleanResult("isNumber('abcde')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumberWithInput1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('[pos=1]')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumberWithInput2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('[pos=2]')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumberWithInput3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('[pos=3]')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumberWithVariables1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{		
		assertEquals(true, Evaluator.getBooleanResult("isNumber('[var=a]')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumberWithVariables2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('[var=b]')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumberWithVariables3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(true, Evaluator.getBooleanResult("isNumber('[var=c]')", exerVar, fillIn));
	}
	
	@Test
	public void testIsNumberWithWrongInputCharacter() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(false, Evaluator.getBooleanResult("isNumber(a)", exerVar, fillIn));
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testIsNumberWithTwoArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getBooleanResult("isNumber(3/2 , 0)", exerVar, fillIn);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testIsNumberWithThreeArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getBooleanResult("isNumber(3/2, 1/3, 3/1)", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testIsNumberWithMissingExerciseVariable() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getBooleanResult("isNumber('[var=j]')", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testIsNumberWithMissingInput() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getBooleanResult("isNumber('[pos=42]')", exerVar, fillIn);
	}
}
