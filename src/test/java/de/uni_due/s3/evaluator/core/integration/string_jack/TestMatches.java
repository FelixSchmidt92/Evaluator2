package de.uni_due.s3.evaluator.core.integration.string_jack;

import static org.junit.Assert.assertTrue;
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

public class TestMatches extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>abababa</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMI>6</OMI></OMOBJ>"));
			fillInVariableMap.put(9, OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
			
			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>abababa</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMI>6</OMI></OMOBJ>"));
			exerciseVariableMap.put("i", OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testMatches1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('abababa','abababa')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatches2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('abcdefghijklmnopqrstuvwxyz','abcdefghijklmnopqrstuvwxyz')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatches3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('5', '[0-6]+')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatches4() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('Ab3', '[A-Za-z0-9]+')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatches5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('2', '\\d')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatches6() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("matches('abc', '\\d')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatchesAndInput1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('abababa','[pos=7]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatchesAndInput2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('5','[0-[pos=8]]+')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatchesAndInput3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("matches('[pos=9]', '\\d')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatchesWithVariables1() throws EvaluatorException, OpenMathException {	
		assertTrue(Evaluator.getBooleanResult("matches('abababa','[var=g]')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatchesWithVariables2() throws EvaluatorException, OpenMathException {	
		assertTrue(Evaluator.getBooleanResult("matches('5', '[0-[var=h]]+')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test
	public void testMatchesWithVariables3() throws EvaluatorException, OpenMathException {	
		assertTrue(Evaluator.getBooleanResult("matches('[var=i]', '\\d')", exerciseVariableMap, fillInVariableMap));
	}
	
	@Test(expected=ParserException.class)
	public void testMatchesWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("matches(ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testMatchesWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("matches('abc')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testmatchesWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("matches('str', '[i]+', 'string')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedExerciseVariableException.class)
	public void testMatchesWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("matches('[var=j]', '[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=UndefinedFillInVariableException.class)
	public void testMatchesWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("matches('[pos=42]', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}