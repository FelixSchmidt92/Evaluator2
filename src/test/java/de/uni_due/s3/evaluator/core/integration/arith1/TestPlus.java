package de.uni_due.s3.evaluator.core.integration.arith1;

import static org.junit.Assert.assertEquals;
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
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPlus extends TestIntegration {

	@Before
	public void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>3.5</OMI></OMOBJ>"));

			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>10</OMI></OMOBJ>"));
			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>3.5</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testAddition1() throws EvaluatorException, OpenMathException {
		assertEquals(10, Evaluator.getNumberResult("6+4", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAddition2() throws EvaluatorException, OpenMathException {
		assertEquals(10.2, Evaluator.getNumberResult("7.3+2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAddition3() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("0.5 + 0.5", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAddition4() throws EvaluatorException, OpenMathException {
		assertEquals(11, Evaluator.getNumberResult("0.5 + 0.5 + 10", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithNegativeNumber1() throws EvaluatorException, OpenMathException {
		assertEquals(4.4, Evaluator.getNumberResult("7.3+(-2.9)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithNegativeNumber2() throws EvaluatorException, OpenMathException {
		assertEquals(-5.3, Evaluator.getNumberResult("-7.3+2", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithNegativeNumber3() throws EvaluatorException, OpenMathException {
		assertEquals(-8, Evaluator.getNumberResult("-2+(-6)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(9, Evaluator.getNumberResult("6+[var=a]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(12.9, Evaluator.getNumberResult("[var=b]+2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(10.5, Evaluator.getNumberResult("[var=c]+7", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(9, Evaluator.getNumberResult("6+[pos=1]", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(12.9, Evaluator.getNumberResult("[pos=2]+2.9", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(10.5, Evaluator.getNumberResult("[pos=3]+7", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithEncapsulation1() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("1 + (1 + 1)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithEncapsulation2() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("1 + (1 + (1 + 1))", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testAdditionWithEncapsulation3() throws EvaluatorException, OpenMathException {
		assertEquals(6,
				Evaluator.getNumberResult("1 + (1 + (1 + (1 + (1 + 1))))", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testAdditionWithEncapsulation4() throws EvaluatorException, OpenMathException {
		assertEquals(6,
				Evaluator.getNumberResult("((((1 + 1) + 1) + 1) + 1) + 1", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testAdditionWithInputCharacter() throws EvaluatorException, OpenMathException {
		OMA result = Evaluator.evaluate("6 + a", exerciseVariableMap, fillInVariableMap).getOMA();
		assertEquals("<OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>6</OMI><OMV name=\"a\"/></OMA>", result.toString());
	}

	@Test
	public void testAdditionWithInputCharAsString() throws EvaluatorException, OpenMathException {
		OMA result = Evaluator.evaluate("6 + 'a'", exerciseVariableMap, fillInVariableMap).getOMA();
		assertEquals("<OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>6</OMI><OMV name=\"a\"/></OMA>", result.toString());
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testAdditionWithWrongInputString() throws EvaluatorException, OpenMathException {
		OMA result = Evaluator.evaluate("6 + 'abcd'", exerciseVariableMap, fillInVariableMap).getOMA();
		assertEquals("<OMA><OMS name=\"plus\" cd=\"arith1\"/><OMI>6</OMI><OMV name=\"a\"/></OMA>", result.toString());
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testAdditionWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[var=j] + 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testAdditionWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("[pos=42] + 1", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}