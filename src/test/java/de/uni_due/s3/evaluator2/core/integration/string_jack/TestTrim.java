package de.uni_due.s3.evaluator2.core.integration.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestTrim extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>hello</OMSTR></OMOBJ>"));

			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>hello</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testTrim1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")),
				Evaluator.evaluate("trim(' hello')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testTrim2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")),
				Evaluator.evaluate("trim('hello ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testTrim3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")),
				Evaluator.evaluate("trim(' hello ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testTrim4() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hel lo")),
				Evaluator.evaluate("trim(' hel lo ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testTrimWithExpression1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("w o r k")),
				Evaluator.evaluate("trim(trim(' w o r k '))", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testTrimWithExpression2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("w o r k")),
				Evaluator.evaluate("trim(trim(trim(' w o r k ')))", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testTrimWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")),
				Evaluator.evaluate("trim(' [pos=7] ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testTrimWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("hello")),
				Evaluator.evaluate("trim(' [var=g] ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testTrimWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("trim(ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testTrimWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("trim(' a', 'a ')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testTrimWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("trim(' a', 'a ', ' a ')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testTrimWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("trim('[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testTrimWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("trim('[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}