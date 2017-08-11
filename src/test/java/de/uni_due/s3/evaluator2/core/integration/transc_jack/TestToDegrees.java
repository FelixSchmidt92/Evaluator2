package de.uni_due.s3.evaluator2.core.integration.transc_jack;

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

public class TestToDegrees extends TestIntegration {

	private static double PI = Math.PI;
	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	
	@BeforeClass
	public static void beforeTest(){
		OMOBJ piFourth = new OMOBJ();
		OMOBJ piHalf = new OMOBJ();
		OMOBJ pi = new OMOBJ();
		OMOBJ twoPi = new OMOBJ();
		OMOBJ one = new OMOBJ();
		piFourth.setOMF(OMCreator.createOMF(PI/4));
		piHalf.setOMF(OMCreator.createOMF(PI/2));
		pi.setOMF(OMCreator.createOMF(PI));
		twoPi.setOMF(OMCreator.createOMF(2*PI));
		one.setOMI(OMCreator.createOMI(1));

		fillIn.put(45, piFourth);
		fillIn.put(90, piHalf);
		fillIn.put(1, one);
		
		exerVar.put("degc", pi);
		exerVar.put("degd", twoPi);
		exerVar.put("b", one);
	}
	
	@Test public void testToDegrees1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(540 , Evaluator.getNumberResult("toDegrees("+3*PI+")", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegrees2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(360 , Evaluator.getNumberResult("toDegrees("+2*PI+")", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegrees3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(180 , Evaluator.getNumberResult("toDegrees("+PI+")", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegrees4() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(-180 , Evaluator.getNumberResult("toDegrees("+-PI+")", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegrees5() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(90 , Evaluator.getNumberResult("toDegrees("+PI/2+")", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegrees6() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(45 , Evaluator.getNumberResult("toDegrees("+PI/4+")", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegrees7() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(180/PI , Evaluator.getNumberResult("toDegrees(1)", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegreesWithInput1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(90 , Evaluator.getNumberResult("toDegrees('[pos=90]')", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegreesWithInput2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(45 , Evaluator.getNumberResult("toDegrees('[pos=45]')", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegreesWithInput3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(180/PI , Evaluator.getNumberResult("toDegrees('[pos=1]')", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegreesWithVariables1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(360 , Evaluator.getNumberResult("toDegrees('[var=degd]')", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegreesWithVariables2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(180 , Evaluator.getNumberResult("toDegrees('[var=degc]')", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegreesWithVariables3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(180/PI , Evaluator.getNumberResult("toDegrees('[var=b]')", exerVar, fillIn), 0);
	}
	
	@Test public void testToDegreesWithVariables4() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(180 , Evaluator.getNumberResult("toDegrees('[var=PI]')", exerVar, fillIn), 0);
	}
	
	@Test
	public void testToDegreesWithPointNumbers1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(.2*180/(PI) , Evaluator.getNumberResult("toDegrees(0.2)", exerVar, fillIn), 0);	
	}
	
	@Test
	public void testToDegreesWithPointNumbers2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(.1*180/(-PI) , Evaluator.getNumberResult("toDegrees(-0.1)", exerVar, fillIn), 0);	
	}
	
	@Test
	public void testToDegreesWithExpressions1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(0 , Evaluator.getNumberResult("toDegrees(toDegrees(0))", exerVar, fillIn), 0);
	}
	
	@Test
	public void testToDegreesWithExpressions2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertEquals(0 , Evaluator.getNumberResult("toDegrees(toDegrees(toDegrees(0)))", exerVar, fillIn), 0);
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testToDegreesWithWrongInputCharacter() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("toDegrees(a)", exerVar, fillIn);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testToDegreesWithTwoArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("toDegrees(4, 3)", exerVar, fillIn);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testToDegreesWithThreeArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("toDegrees(2, 3, 4)", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testToDegreesWithMissingExerciseVariable() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("toDegrees('[var=j]')", exerVar, fillIn);
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testToDegreesWithMissingInput() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.getNumberResult("toDegrees('[pos=42]')", exerVar, fillIn);
	}
}