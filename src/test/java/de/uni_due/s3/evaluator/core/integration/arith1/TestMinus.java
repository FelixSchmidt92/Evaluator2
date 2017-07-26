package de.uni_due.s3.evaluator.core.integration.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMinus extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>9.78</OMI></OMOBJ>"));
			fillInVariableMap.put(4, OMConverter.toObject("<OMOBJ><OMI>2.15</OMI></OMOBJ>"));

			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>9.75</OMI></OMOBJ>"));
			exerciseVariableMap.put("d", OMConverter.toObject("<OMOBJ><OMI>2.48</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testSubstraction1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("6-4", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstraction2() throws EvaluatorException, OpenMathException {
		assertEquals(4.4, Evaluator.getNumberResult("7.3-2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstraction3() throws EvaluatorException, OpenMathException {
		assertEquals(-7, Evaluator.getNumberResult("3-10", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstraction4() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("7.3-2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstraction5() throws EvaluatorException, OpenMathException {
		assertEquals(7.3, Evaluator.getNumberResult("7.3-0", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstraction6() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("0-0", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("6-[var=a]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(7.1, Evaluator.getNumberResult("[var=b]-2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(-7, Evaluator.getNumberResult("3-[var=b]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithVariables4() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("7.3-[var=a]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithVariables5() throws EvaluatorException, OpenMathException {
		assertEquals(7.27, Evaluator.getNumberResult("[var=c]-[var=d]", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testSubstractionWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("6-[pos=1]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(7.1, Evaluator.getNumberResult("[pos=2]-2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(-7, Evaluator.getNumberResult("[pos=1]-10", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithInput4() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("7.3-[pos=1]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithInput5() throws EvaluatorException, OpenMathException {
		assertEquals(7.63, Evaluator.getNumberResult("[pos=3]-[pos=4]", exerciseVariableMap, fillInVariableMap),
				0.0001); // Ergebnis hier 7.629999999999
	}

	@Test
	public void testSubstractionWithNegativeNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("3--2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithNegativeNumbers2() throws EvaluatorException, OpenMathException {
		assertEquals(5, Evaluator.getNumberResult("4--1", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testSubstractionWithNegativeNumbers3() throws EvaluatorException, OpenMathException {
		assertEquals(7, Evaluator.getNumberResult("7--0", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("5 - (3 - 1)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("2 - (2 - (2 - 1))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertEquals(1,
				Evaluator.getNumberResult("2 - (2 - (2 - (2 - (2 - 1))))", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testAdditionWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertEquals(1,
				Evaluator.getNumberResult("((((6 - 1) - 1) - 1) - 1) - 1", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testSubstractionWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("6 - a", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testSubstractionWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("6 - 'a'", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testSubstractionWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[var=j] - 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testSubstractionWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[pos=42] - 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}