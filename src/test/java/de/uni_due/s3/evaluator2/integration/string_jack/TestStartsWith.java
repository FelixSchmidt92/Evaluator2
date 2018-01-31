package de.uni_due.s3.evaluator2.integration.string_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestStartsWith extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>ll</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			fillInVariableMap.put(9, OMConverter.toObject("<OMOBJ><OMSTR>hello</OMSTR></OMOBJ>"));

			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			exerciseVariableMap.put("ll", OMConverter.toObject("<OMOBJ><OMSTR>ll</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("hello", OMConverter.toObject("<OMOBJ><OMSTR>hello</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testStartsWith1() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("startsWith('hello','he',0)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWith2() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("startsWith('hello','ll',2)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWith3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("startsWith('hello','o',4)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWith4() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("startsWith('hello','ll',0)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWith5() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("startsWith('','',0)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWith6() throws EvaluatorException, OpenMathException {
		assertTrue(!Evaluator.getBooleanResult("startsWith('',' ',0)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWithInput1() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("startsWith('hello','he',[pos=8])", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWithInput2() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("startsWith('hello','[pos=7]',2)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWithInput3() throws EvaluatorException, OpenMathException {
		assertTrue(Evaluator.getBooleanResult("startsWith('[pos=9]','o',4)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWithVariables1() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("startsWith('hello','he',[var=g])", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWithVariables2() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("startsWith('hello','[var=ll]',2)", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testStartsWithVariables3() throws EvaluatorException, OpenMathException {
		assertTrue(
				Evaluator.getBooleanResult("startsWith('[var=hello]','o',4)", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testStartsWithWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("startsWith(ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testStartsWithWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("startsWith('hello')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test
	public void testStartsWithWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("startsWith('hello', 'he')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testStartsWithWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("startsWith('[var=j]', '[var=j]', '[var=j]')", exerciseVariableMap,
				fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testStartsWithWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("startsWith('[pos=42]', '[pos=42]', '[pos=42]')", exerciseVariableMap,
				fillInVariableMap);
		fail();
	}
}