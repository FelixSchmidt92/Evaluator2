package de.uni_due.s3.evaluator.core.integration.integer1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRemainder extends TestIntegration {

	HashMap<Integer, OMOBJ> remainderFillInVariableMap = new HashMap<>();
	HashMap<String, OMOBJ> remainderExerciseVariableMap = new HashMap<>();
	
	@Before 
	public void beforeTest() {
		try {
			remainderFillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			remainderFillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));

			remainderExerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			remainderExerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}
	
	@Test
	public void testModulus1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("5%5", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulus2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("1%3", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulus3() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("7%4", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulus4() throws EvaluatorException, OpenMathException {
		assertEquals(6, Evaluator.getNumberResult("6%7", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulus5() throws EvaluatorException, OpenMathException {
		assertEquals(6, Evaluator.getNumberResult("6%7", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulus6() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("12.0%2.5", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test (expected=FunctionInvalidArgumentException.class)
	public void testModulus7() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("4%0", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test
	public void testModulusWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("[var=a]%3", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("1%[var=a]", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("[var=a]%[var=b]", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);	
	}
	
	@Test
	public void testModulusWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("[pos=1]%3", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("1%[pos=1]", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("[pos=1]%[pos=2]", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithNegativeNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("-2 % 5", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithNegativeNumbers2() throws EvaluatorException, OpenMathException {
		assertEquals(-2.3, Evaluator.getNumberResult("-2.3 % 5", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithNegativeNumbers3() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("-7 % -5", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("1 % (1 % 2)", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("2 % (3 % (4 % 5))", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("1 % (5 % (7 % (9 % (5 % 10))))", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertNotEquals(3, Evaluator.getNumberResult("((3 % 4) % 4) % 3", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test
	public void testModulusWithEncapsulation5() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("((((7 % 10) % 4) % 4) % 4) % 3", remainderExerciseVariableMap, remainderFillInVariableMap), 0.0);
	}
	
	@Test(expected=ParserException.class)
	public void testModulusWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("6 % ab", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testModulusWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("6 % 'a'", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testModulusWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[var=j] % 2", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testModulusWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[pos=42] % 2", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
