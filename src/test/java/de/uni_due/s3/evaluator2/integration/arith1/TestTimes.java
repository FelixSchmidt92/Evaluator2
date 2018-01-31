package de.uni_due.s3.evaluator2.integration.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestTimes extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>2.5</OMI></OMOBJ>"));
			fillInVariableMap.put(4, OMConverter.toObject("<OMOBJ><OMI>4.3</OMI></OMOBJ>"));

			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>2.5</OMI></OMOBJ>"));
			exerciseVariableMap.put("d", OMConverter.toObject("<OMOBJ><OMI>4.3</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testMultiplication1() throws EvaluatorException, OpenMathException {
		assertEquals(24, Evaluator.getNumberResult("6*4", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplication2() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("7.3*2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplication3() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("7.3*0", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplication4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("0*0", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplication5() throws EvaluatorException, OpenMathException {
		assertEquals(21.17, Evaluator.getNumberResult("7.3*2.9", exerciseVariableMap, fillInVariableMap), 0.0001);
	}

	@Test
	public void testMultiplicationWithNegativeNumber1() throws EvaluatorException, OpenMathException {
		assertEquals(-29, Evaluator.getNumberResult("10*(-2.9)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithNegativeNumber2() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("-7.3*2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(18, Evaluator.getNumberResult("6*[var=a]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(29, Evaluator.getNumberResult("[var=b]*2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithVariables3() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("7.3*[var=a]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithVariables4() throws EvaluatorException, OpenMathException {
		assertEquals(10.75, Evaluator.getNumberResult("[var=c]*[var=d]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(18, Evaluator.getNumberResult("6*[pos=1]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(29, Evaluator.getNumberResult("[pos=2]*2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithInput3() throws EvaluatorException, OpenMathException {
		assertNotEquals(1, Evaluator.getNumberResult("7.3*[pos=1]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithInput4() throws EvaluatorException, OpenMathException {
		assertEquals(10.75, Evaluator.getNumberResult("[pos=3]*[pos=4]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertEquals(8, Evaluator.getNumberResult("2 * (2 * 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertEquals(16, Evaluator.getNumberResult("2 * (2 * (2 * 2))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMultiplicationWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertEquals(64,
				Evaluator.getNumberResult("2 * (2 * (2 * (2 * (2 * 2))))", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMultiplicationWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertEquals(64,
				Evaluator.getNumberResult("((((2 * 2) * 2) * 2) * 2) * 2", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMultiplicationWithInputCharacter() throws EvaluatorException, OpenMathException {
		OMA result = Evaluator.evaluate("6 * a", exerciseVariableMap, fillInVariableMap).getOMA();
		assertEquals("<OMA><OMS name=\"times\" cd=\"arith1\"/><OMI>6</OMI><OMV name=\"a\"/></OMA>", result.toString());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMultiplicationWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("6 * 'aabcd'", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testMultiplicationWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[var=j] * 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testMultiplicationWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[pos=42] * 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}