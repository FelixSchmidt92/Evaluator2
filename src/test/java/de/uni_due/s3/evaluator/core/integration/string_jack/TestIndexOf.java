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

public class TestIndexOf extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>str</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMSTR>anotherstring</OMSTR></OMOBJ>"));
			fillInVariableMap.put(9, OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
			
			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>str</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMSTR>anotherstring</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("i", OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testIndexOf1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("indexOf('abcdef','def', 0)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOf2() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("indexOf('abc def','def', 2)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOf3() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("indexOf('abc_def','def', 3)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOf4() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("indexOf('2 c 4e6','4e6', 1)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test (expected=FunctionInvalidArgumentException.class)
	public void testIndexOf5() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("indexOf('2c4e 6','4e6', -1)", exerciseVariableMap, fillInVariableMap),0.0);
		fail();
	}
	
	@Test
	public void testIndexOfWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("indexOf('[pos=8]','str', 3)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOfWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("indexOf('[pos=8]','[pos=7]', 2)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOfWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("indexOf('[pos=8]','[pos=7]', [pos=9])", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOfWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("indexOf('[var=h]','str', 3)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOfWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("indexOf('[var=h]','[var=g]', 2)", exerciseVariableMap, fillInVariableMap),0.0);
	}
	
	@Test
	public void testIndexOfWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("indexOf('[var=h]','[var=g]', [var=i])", exerciseVariableMap, fillInVariableMap),0.0);
	}

	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testIndexOfWithWrongInputPointNumber() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf('abcd5', 5.1, 1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testIndexOfWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf('abcd5', a, 1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testIndexOfWithTwoRationalAndOneTextArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf('abcd5', 5, 1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testIndexOfWithThreeRationalArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf(5, 5, 0)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testIndexOfWithThreeTextArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf('str', 'str', '0')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testIndexOfWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf(1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testIndexOfWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf('string', 1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testIndexOfWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf('[var=j]', 'str', 2)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testIndexOfWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("indexOf('[pos=42]', 'str', 2)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}