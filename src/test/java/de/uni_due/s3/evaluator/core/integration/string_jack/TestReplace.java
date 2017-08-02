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
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestReplace extends TestIntegration {

	private char cMax = Character.MAX_VALUE;
	private char cMin = Character.MIN_VALUE;

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(7, OMConverter.toObject("<OMOBJ><OMSTR>HaveQaQniceQday</OMSTR></OMOBJ>"));
			fillInVariableMap.put(8, OMConverter.toObject("<OMOBJ><OMSTR>a</OMSTR></OMOBJ>"));
			fillInVariableMap.put(9, OMConverter.toObject("<OMOBJ><OMSTR>b</OMSTR></OMOBJ>"));

			exerciseVariableMap.put("g", OMConverter.toObject("<OMOBJ><OMSTR>HaveQaQniceQday</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("h", OMConverter.toObject("<OMOBJ><OMSTR>a</OMSTR></OMOBJ>"));
			exerciseVariableMap.put("i", OMConverter.toObject("<OMOBJ><OMSTR>b</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testReplace1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("Hello$$World!$")),
				Evaluator.evaluate("replace('Hello  World! ', ' ', '$')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplace2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("Have a nice day ")),
				Evaluator.evaluate("replace('Have a nice day!', '!', ' ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplace3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("1010101")),
				Evaluator.evaluate("replace('a0a0a0a', 'a', '1')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplace4() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("`0`0`0`")),
				Evaluator.evaluate("replace('a0a0a0a', 'a', '`')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("Have a nice day")),
				Evaluator.evaluate("replace('[pos=7]', 'Q', ' ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("H veQ QniceQd y")),
				Evaluator.evaluate("replace('[pos=7]', '[pos=8]', ' ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HbveQbQniceQdby")),
				Evaluator.evaluate("replace('[pos=7]', '[pos=8]', '[pos=9]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("Have a nice day")),
				Evaluator.evaluate("replace('[var=g]', 'Q', ' ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("H veQ QniceQd y")),
				Evaluator.evaluate("replace('[var=g]', '[var=h]', ' ')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HbveQbQniceQdby")),
				Evaluator.evaluate("replace('[var=g]', '[var=h]', '[var=i]')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithCharacterMaxMinValue1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("" + cMax + "0")),
				Evaluator.evaluate("replace('a0', 'a', '" + cMax + "')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithCharacterMaxMinValue2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("" + cMin + "0")),
				Evaluator.evaluate("replace('a0', 'a', '" + cMin + "')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void testReplaceWithWrongInputSecondArgumentEmpty() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("aaaaa")),
				Evaluator.evaluate("replace('aa', '', 'a')", exerciseVariableMap, fillInVariableMap));
	}

	@Test
	public void tesReplaceWithThirdArgumentEmpty() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("a")),
				Evaluator.evaluate("replace('a ', ' ', '')", exerciseVariableMap, fillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testReplaceWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("replace(ab, ab, ab)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testReplaceWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("replace('a')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testReplaceWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("replace('a', 'b')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testReplaceWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("replace('[var=j]', '[var=j]', '[var=j]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testReplaceWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("replace('[pos=42]', '[pos=42]', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}