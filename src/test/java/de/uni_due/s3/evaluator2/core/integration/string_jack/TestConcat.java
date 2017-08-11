package de.uni_due.s3.evaluator2.core.integration.string_jack;

import java.util.HashMap;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestConcat extends TestIntegration {
	static HashMap<Integer, OMOBJ> concatFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> concatExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		try {
			concatFillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMSTR>Hallo</OMSTR></OMOBJ>"));

			concatExerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMSTR>Hallo</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testConcat1() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HalloHallo")),
				Evaluator.evaluate("concat('Hallo','Hallo')", concatExerciseVariableMap, concatFillInVariableMap));
	}

	@Test
	public void testConcat2() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("Hallo  Hallo")),
				Evaluator.evaluate("concat('Hallo ',' Hallo')", concatExerciseVariableMap, concatFillInVariableMap));
	}

	@Test
	public void testConcat3() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR(" ")),
				Evaluator.evaluate("concat(' ','')", concatExerciseVariableMap, concatFillInVariableMap));
	}

	@Test
	public void testConcat4() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR(" ")),
				Evaluator.evaluate("concat('',' ')", concatExerciseVariableMap, concatFillInVariableMap));
	}

	@Test
	public void testConcatWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HalloHallo")),
				Evaluator.evaluate("concat('[pos=1]','[pos=1]')", concatExerciseVariableMap, concatFillInVariableMap));
	}

	@Test
	public void testConcatWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("HalloHallo")),
				Evaluator.evaluate("concat('[var=a]','[var=a]')", concatExerciseVariableMap, concatFillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testConcatWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("concat(ab,'abcd')", concatExerciseVariableMap, concatFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testConcatWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("concat('abcd')", concatExerciseVariableMap, concatFillInVariableMap);
		fail();
	}

	@Test
	public void testConcatWithOneRationalAndOneTextArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("concat(12, 'abcd')", concatExerciseVariableMap, concatFillInVariableMap);
	}

	@Test
	public void testConcatWithTwoRationalArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("concat(12, 14)", concatExerciseVariableMap, concatFillInVariableMap);
	}

	@Test
	public void testConcatWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("concat('abcd', 'efg', 'hij')", concatExerciseVariableMap, concatFillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testConcatWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("concat('[var=j]','abcd')", concatExerciseVariableMap, concatFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testConcatWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("concat([pos=42],'abcd')", concatExerciseVariableMap, concatFillInVariableMap);
		fail();
	}
}