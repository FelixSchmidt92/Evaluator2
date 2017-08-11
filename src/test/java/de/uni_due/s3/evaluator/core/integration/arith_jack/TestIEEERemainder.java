package de.uni_due.s3.evaluator.core.integration.arith_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.*;
import de.uni_due.s3.evaluator.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIEEERemainder extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>20</OMI></OMOBJ>"));
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>-10</OMI></OMOBJ>"));

			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>0</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>20</OMI></OMOBJ>"));
			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>-10</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testIEEERemainder1() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("IEEEremainder(3, 2)", exerciseVariableMap, fillInVariableMap), 0);
	}

	@Test
	public void testIEEERemainder2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("IEEEremainder(4, 2)", exerciseVariableMap, fillInVariableMap), 0);
	}

	@Test
	public void testIEEERemainder3() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("IEEEremainder(10, 3)", exerciseVariableMap, fillInVariableMap), 0);
	}

	@Test
	public void testIEEERemainder4() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("IEEEremainder(11, 3)", exerciseVariableMap, fillInVariableMap), 0);
	}

	@Test
	public void testIEEERemainder5() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("IEEEremainder(27, 4)", exerciseVariableMap, fillInVariableMap), 0);
	}

	@Test
	public void testIEEERemainder6() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("IEEEremainder(28, 5)", exerciseVariableMap, fillInVariableMap), 0);
	}

	@Test // It returns for Example 1.800000000XXX not 1.8
	public void testIEEERemainderWithPointNumberResults1() throws EvaluatorException, OpenMathException {
		assertEquals(1.8, Evaluator.getNumberResult("IEEEremainder(17.8, 4)", exerciseVariableMap, fillInVariableMap),
				0.00001);
	}

	@Test
	public void testIEEERemainderWithPointNumberResults2() throws EvaluatorException, OpenMathException {
		assertEquals(1.4, Evaluator.getNumberResult("IEEEremainder(17.8, 4.1)", exerciseVariableMap, fillInVariableMap),
				0.00001);
	}

	@Test
	public void testIEEERemainderWithPointNumberResults3() throws EvaluatorException, OpenMathException {
		assertEquals(0.1,
				Evaluator.getNumberResult("IEEEremainder(-16.3, 4.1)", exerciseVariableMap, fillInVariableMap), 0.00001);
	}

	@Test
	public void testIEEERemainderWithPointNumberResults4() throws EvaluatorException, OpenMathException {
		assertEquals(1.4,
				Evaluator.getNumberResult("IEEEremainder(17.8, -4.1)", exerciseVariableMap, fillInVariableMap), 0.00001);
	}

	@Test
	public void testIEEERemainderWithPointNumberResults5() throws EvaluatorException, OpenMathException {
		assertEquals(-1.4,
				Evaluator.getNumberResult("IEEEremainder(-17.8, -4.1)", exerciseVariableMap, fillInVariableMap), 0.00001);
	}

	@Test
	public void testIEEERemainderWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("IEEEremainder([pos=1], 4)", exerciseVariableMap, fillInVariableMap),
				0);

	}

	@Test
	public void testIEEERemainderWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0,
				Evaluator.getNumberResult("IEEEremainder([pos=2], [pos=3])", exerciseVariableMap, fillInVariableMap),
				0);
	}

	@Test
	public void testIEEERemainderWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("IEEEremainder([var=a], 4)", exerciseVariableMap, fillInVariableMap),
				0);
	}

	@Test
	public void testIEEERemainderWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0,
				Evaluator.getNumberResult("IEEEremainder([var=b], [var=c])", exerciseVariableMap, fillInVariableMap),
				0);
	}

	@Test
	public void testIEEERemainderWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("IEEEremainder('15', 'IEEEremainder(10, 3)')", exerciseVariableMap,
				fillInVariableMap), 0);

	}

	@Test
	public void testIEEERemainderWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("IEEEremainder('IEEEremainder(10, 3)', 'IEEEremainder(10, 3)')",
				exerciseVariableMap, fillInVariableMap), 0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIEEERemainderWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("IEEEremainder(17.8, a)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIEEERemainderWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("IEEEremainder(8)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIEEERemainderWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("IEEEremainder(3, 2, 8)", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIEEERemainderWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("IEEEremainder('[var=j]', '3')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIEEERemainderWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("IEEEremainder('[pos=42]', '3')", exerciseVariableMap, fillInVariableMap);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testIEEERemainderAtZero() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("IEEEremainder(5, 0)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}