package de.uni_due.s3.evaluator.core.integration.string_jack;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

public class TestEqualsIgnoreCase extends TestIntegration {

	@Before
	public void beforeTest() {
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
	public void testEqualsIgnoreCase1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('hallo','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCase2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('HALLO','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCase3() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equalsIgnoreCase('hallo welt','hallowelt')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCase4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase(' ',' ')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCase5() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("equalsIgnoreCase(' ','  ')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCase6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('','')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCaseWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('[pos=7]','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCaseWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('hallo','[pos=8]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCaseWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('[var=g]','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCaseWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('hallo','[var=h]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEqualsIgnoreCaseWithExpressions() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("equalsIgnoreCase('equalsIgnorCase('1', '1')','equalsIgnorCase('2', '2')')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test (expected=ParserException.class)
	public void testEqualsIgnoreCaseWithWrongCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsIgnoreCase(ab, ab)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test (expected=FunctionInvalidArgumentTypeException.class)
	public void testEqualsIgnoreCaseWithWrongInputRational() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsIgnoreCase(1, 1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsIgnoreCaseWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsIgnoreCase('hallo')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsIgnoreCaseWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsIgnoreCase('hallo', 'test', '123')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testEqualsIgnoreCaseWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsIgnoreCase('[pos=15]','hallo')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test (expected=UndefinedExerciseVariableException.class)
	public void testEqualsIgnoreCaseWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("equalsIgnoreCase('[var=z]','hallo')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}