package de.uni_due.s3.evaluator.core.integration.string_jack;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEquals extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>hallo</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMSTR>Hallo</OMSTR></OMOBJ>"));
			
			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>hallo</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMSTR>Hallo</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test 
	public void testEquals1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equals('hallo','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEquals2() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equals('HALLO','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEquals3() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equals('hallo welt','hallowelt')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEquals4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equals(' ',' ')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEquals5() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equals(' ','  ')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEquals6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equals('','')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEquals7() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equals('1', '1')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEquals8() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equals('3+5','8')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equals('[pos=7]','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsWithInput2() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equals('hallo','[pos=8]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equals('[var=g]','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsWithVariables2() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equals('hallo','[var=h]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsWithExpressions() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equals(equals('a','a'), equals('b','b'))", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test (expected=ParserException.class)
	public void testEqualsWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equals(ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equals('hallo','hallo', 'test')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testEqualsWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equals('[pos=42]','hallo')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testEqualsWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equals('[var=j]','hallo')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}