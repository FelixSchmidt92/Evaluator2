package de.uni_due.s3.evaluator.core.integration.binary_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

public class TestConvertToBinary extends TestIntegration {
	
	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>7</OMI></OMOBJ>"));
			
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>7</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen", e);
		}
	}
	
	@Test 
	public void testConvertToBinary1() throws EvaluatorException, OpenMathException {
		assertEquals(10, Evaluator.getNumberResult("convertToBinary(2)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testConvertToBinary2() throws EvaluatorException, OpenMathException {
		assertEquals(1101101111, Evaluator.getNumberResult("convertToBinary(879)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testConvertToBinary3() throws EvaluatorException, OpenMathException {
		assertNotEquals(-11, Evaluator.getNumberResult("convertToBinary(-3)", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testConvertToBinaryWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(111, Evaluator.getNumberResult("convertToBinary([pos=2])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testConvertToBinaryWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("convertToBinary([pos=1])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testConvertToBinaryWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("convertToBinary([var=a])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test 
	public void testConvertToBinaryWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(111, Evaluator.getNumberResult("convertToBinary([var=b])", exerciseVariableMap, fillInVariableMap),0.0001);
	}
	
	@Test (expected=ParserException.class)
	public void testConvertToBinaryWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("convertToBinary(a)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testConvertToBinaryWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("convertToBinary(3, 2)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testConvertToBinaryWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("convertToBinary(1, 2, 3)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testConvertToBinaryWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("convertToBinary([var=j])", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testConvertToBinaryWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("convertToBinary([pos=42])", exerciseVariableMap, fillInVariableMap);
	}
}