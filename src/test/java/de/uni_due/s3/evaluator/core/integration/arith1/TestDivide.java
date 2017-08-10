package de.uni_due.s3.evaluator.core.integration.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDivide extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testDivision1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("8/4", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivision2() throws EvaluatorException, OpenMathException {
		assertEquals(0.25, Evaluator.getNumberResult("1/4", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivision3() throws EvaluatorException, OpenMathException {
		assertEquals(0.75, Evaluator.getNumberResult("1.5/2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testDivision4() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("1/0", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test
	public void testDivisionWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("[var=a]/3", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("3/[var=a]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("[pos=1]/3", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("3/[pos=1]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithNegativeNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals(-.5, Evaluator.getNumberResult("-1 / 2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithNegativeNumbers2() throws EvaluatorException, OpenMathException {
		assertEquals(-.5, Evaluator.getNumberResult("1 / -2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithNegativeNumbers3() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("-1 / 0.5", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithNegativeNumbers4() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("2.22 / 1.11", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("1 / (1 / 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertEquals(.5, Evaluator.getNumberResult("1 / (1 / (1 / 2))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testDivisionWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertEquals(.5,
				Evaluator.getNumberResult("1 / (1 / (1 / (1 / (1 / 2))))", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testDivisionWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertEquals(.5,
				Evaluator.getNumberResult("((((1 / 2) / 1) / 1) / 1) / 1", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testDivisionWithInputCharacter() throws EvaluatorException, OpenMathException {
		OMA result = Evaluator.evaluate("6 / a", exerciseVariableMap, fillInVariableMap).getOMA();
		assertEquals("<OMA><OMS name=\"divide\" cd=\"arith1\"/><OMI>6</OMI><OMV name=\"a\"/></OMA>", result.toString());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDivisionWithWrongInputString() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("6 / 'aabcd'", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testDivisionWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[var=j] / 2", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testDivisionWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[pos=42] / 2", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}