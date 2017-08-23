package de.uni_due.s3.evaluator2.core.integration.string_jack;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEndsWith extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>testinput</OMSTR></OMOBJ>"));

			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>testinput</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test 
	public void testEndsWith1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('1567','7')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWith2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('hallo','o')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWith3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('hallo','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWith4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('1567.','.' )", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWith5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('test ',' ')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWith6() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('leererVergleich','')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWith7() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("endsWith('hallo','O')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWith8() throws EvaluatorException, OpenMathException {
		assertFalse(Evaluator.getBooleanResult("endsWith('lo','hallo')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWithWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('[pos=7] ','')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWithWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('test01','[pos=1][pos=2]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWithWithInput3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('[pos=7]','put')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWithWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('[var=g] ','')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWithWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('test01','[var=a][var=b]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWithWithVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith('[var=g]','put')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test 
	public void testEndsWithWithExpressions() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("endsWith(endsWith('1', '1'), endsWith('2', '2'))", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test(expected=ParserException.class)
	public void testEndsWithWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("endsWith(ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test
	public void testEndsWithWithTwoRationalArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("endsWith(2, 2)", exerciseVariableMap, fillInVariableMap);
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testEndsWithWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("endsWith('a')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testEndsWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("endsWith('a', 'a', 'a')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testEndsWithWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("endsWith('[var=j]', 'a')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testEndsWithWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("endsWith('[pos=42]', 'a')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}