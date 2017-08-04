package de.uni_due.s3.evaluator.core.integration.string_jack;

import java.util.HashMap;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCompareToIgnoreCase extends TestIntegration {
	HashMap<Integer, OMOBJ> compareToIgnoreCaseFillInVariableMap = new HashMap<>();
	HashMap<String, OMOBJ> compareToIgnoreCaseExerciseVariableMap = new HashMap<>();

	@Before
	public void beforeTest() {
		try {
			compareToIgnoreCaseFillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMSTR>hallo</OMSTR></OMOBJ>"));

			compareToIgnoreCaseExerciseVariableMap.put("a",
					OMConverter.toObject("<OMOBJ><OMSTR>hallo</OMSTR></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testCompareToIgnoreCase1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("compareToIgnoreCase('Hallo','HAlLo')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareToIgnoreCase2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("compareToIgnoreCase('Hallo','Hallo')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareToIgnoreCase3() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("compareToIgnoreCase('jack','JACK')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareToIgnoreCase4() throws EvaluatorException, OpenMathException {
		assertTrue(0 < Evaluator.getNumberResult("compareToIgnoreCase('paluno','JACK')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap));
	}

	@Test
	public void testCompareToIgnoreCase5() throws EvaluatorException, OpenMathException {
		assertTrue(0 > Evaluator.getNumberResult("compareToIgnoreCase('JACK','paluno')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap));
	}
	
	//FIXME Polynome in strings umwandeln (generelle Möglichkeit)
	@Test
	public void testCompareToIgnoreCase6() throws EvaluatorException, OpenMathException {
		assertNotEquals(0, Evaluator.getNumberResult("compareToIgnoreCase('2x+2y','2*(x+y)')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap), 0.0);
	}

	@Test
	public void testCompareToIgnoreCaseWithInput() throws EvaluatorException, OpenMathException {
		assertTrue(0 > Evaluator.getNumberResult("compareToIgnoreCase('[pos=1]','Hello')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap));
	}

	@Test
	public void testCompareToIgnoreCaseWithVariables() throws EvaluatorException, OpenMathException {
		assertTrue(0 > Evaluator.getNumberResult("compareToIgnoreCase('[var=a]','Hello')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap));
	}

	@Test(expected = ParserException.class)
	public void testCompareToIgnoreCaseWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("compareToIgnoreCase(ba,'hallo')", compareToIgnoreCaseExerciseVariableMap,
				compareToIgnoreCaseFillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToIgnoreCaseWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("compareToIgnoreCase('hallo')", compareToIgnoreCaseExerciseVariableMap,
				compareToIgnoreCaseFillInVariableMap);
		fail();
	}

	@Test
	public void testCompareToIgnoreCaseWithOneRationalAndOneTextArgument()
			throws EvaluatorException, OpenMathException {
		assertNotEquals(0, Evaluator.getNumberResult("compareToIgnoreCase(12, 'hallo')", compareToIgnoreCaseExerciseVariableMap,
				compareToIgnoreCaseFillInVariableMap),0.0);
	}

	@Test
	public void testCompareToIgnoreCaseWithTwoRationalArguments() throws EvaluatorException, OpenMathException {
		assertNotEquals(0, Evaluator.getNumberResult("compareToIgnoreCase(17, 8)", compareToIgnoreCaseExerciseVariableMap,
				compareToIgnoreCaseFillInVariableMap),0.0);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToIgnoreCaseWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("compareToIgnoreCase('hallo', 'hallo', 'hallo')",
				compareToIgnoreCaseExerciseVariableMap, compareToIgnoreCaseFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testCompareToIgnoreCaseWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("compareToIgnoreCase('[var=j]', 'test')", compareToIgnoreCaseExerciseVariableMap,
				compareToIgnoreCaseFillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testCompareToIgnoreCaseWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("compareToIgnoreCase('[pos=42]', 'test')", compareToIgnoreCaseExerciseVariableMap,
				compareToIgnoreCaseFillInVariableMap);
		fail();
	}
}