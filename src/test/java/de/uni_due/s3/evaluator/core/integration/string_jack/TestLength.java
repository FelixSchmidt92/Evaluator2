package de.uni_due.s3.evaluator.core.integration.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLength extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>Haveaniceday</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMSTR>stillworking</OMSTR></OMOBJ>"));
			
			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>Haveaniceday</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMSTR>stillworking</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testLength1() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("length('abcde')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testLength2() throws EvaluatorException, OpenMathException {
		assertEquals(14, Evaluator.getNumberResult("length('another string')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testLength3() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("length('')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testLength4() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("length('  ')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testLengthWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(12, Evaluator.getNumberResult("length('[pos=7]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testLengthWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(12, Evaluator.getNumberResult("length('[pos=8]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testLengthWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(12, Evaluator.getNumberResult("length('[var=g]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test
	public void testLengthWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(12, Evaluator.getNumberResult("length('[var=h]')", exerciseVariableMap, fillInVariableMap), 0.0);
	}
	
	@Test(expected=ParserException.class)
	public void testLengthWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("length(ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testLengthWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("length('abab', 'abab')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testLengthWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("length('abab', 'ab', 'ab')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testLengthWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("length('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testLengthWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("length('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}