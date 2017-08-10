package de.uni_due.s3.evaluator.core.integration.string_jack;

import java.util.HashMap;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCompareTo extends TestIntegration {
	static HashMap<Integer, OMOBJ> compareToFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> compareToExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		try {
			compareToFillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMSTR>test</OMSTR></OMOBJ>"));

			compareToExerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMSTR>test</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testCompareTo1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("compareTo('Hallo','Hallo')", compareToExerciseVariableMap,
				compareToFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareTo2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("compareTo('HAlLo','HAlLo')", compareToExerciseVariableMap,
				compareToFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareTo3() throws EvaluatorException, OpenMathException {
		assertTrue(0 < Evaluator.getNumberResult("compareTo('jack','JACK')", compareToExerciseVariableMap,
				compareToFillInVariableMap));
	}

	@Test
	public void testCompareTo4() throws EvaluatorException, OpenMathException {
		assertTrue(0 < Evaluator.getNumberResult("compareTo('paluno','JACK')", compareToExerciseVariableMap,
				compareToFillInVariableMap));
	}

	@Test
	public void testCompareTo5() throws EvaluatorException, OpenMathException {
		assertTrue(0 > Evaluator.getNumberResult("compareTo('JACK','paluno')", compareToExerciseVariableMap,
				compareToFillInVariableMap));
	}

	@Test
	public void testCompareTo6() throws EvaluatorException, OpenMathException {
		assertNotEquals(0, Evaluator.getNumberResult("compareTo('2*x+2*y','2*(x+y)')", compareToExerciseVariableMap,
				compareToFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareToWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("compareTo('[pos=1]','test')", compareToExerciseVariableMap,
				compareToFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareToWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("compareTo('[var=a]','test')", compareToExerciseVariableMap,
				compareToFillInVariableMap), 0.0);
	}

	@Test(expected = ParserException.class)
	public void testCompareToWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("compareTo(ab,'test')", compareToExerciseVariableMap, compareToFillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("compareTo('Hallo')", compareToExerciseVariableMap, compareToFillInVariableMap);
		fail();
	}

	@Test
	public void testCompareToWithOneRationalAndOneTextArgument() throws EvaluatorException, OpenMathException {
		assertNotEquals(Evaluator.getNumberResult("compareTo(12, 'rational')", compareToExerciseVariableMap, compareToFillInVariableMap),0.0);
	}

	@Test
	public void testCompareToWithTwoRationalArguments() throws EvaluatorException, OpenMathException {
		assertNotEquals(0, Evaluator.getNumberResult("compareTo(1, 2)", compareToExerciseVariableMap, compareToFillInVariableMap),0.0);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("compareTo('hallo', 'hello', 'hillo')", compareToExerciseVariableMap,
				compareToFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testCompareToWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("compareTo('[var=j]', 'test')", compareToExerciseVariableMap, compareToFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testCompareToWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("compareTo('[pos=42]', 'test')", compareToExerciseVariableMap, compareToFillInVariableMap);
		fail();
	}
}