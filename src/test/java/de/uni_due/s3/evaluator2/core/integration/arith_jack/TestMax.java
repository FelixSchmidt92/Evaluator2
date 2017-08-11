package de.uni_due.s3.evaluator2.core.integration.arith_jack;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMax extends TestIntegration {

	@BeforeClass
	public static void beforeTest() {
		try {
			fillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));
			fillInVariableMap.put(2, OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
			fillInVariableMap.put(3, OMConverter.toObject("<OMOBJ><OMI>-1</OMI></OMOBJ>"));

			exerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>1</OMI></OMOBJ>"));
			exerciseVariableMap.put("b", OMConverter.toObject("<OMOBJ><OMI>2</OMI></OMOBJ>"));
			exerciseVariableMap.put("c", OMConverter.toObject("<OMOBJ><OMI>-1</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testMax1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("max(1, 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMax2() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("max(3, 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMax3() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("max(1, 1)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMax4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("max(0, 0)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMax5() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("max(-2, 0)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMax6() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("max(-2, 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMaxWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("max([var=a], [var=b])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMaxWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("max([var=a], [var=c])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMaxWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("max([pos=1], [pos=2])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMaxWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("max([pos=1], [pos=3])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMaxWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(12, Evaluator.getNumberResult("max(max(12,2), 4)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMaxWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(12, Evaluator.getNumberResult("max(max(12,2), max(5, 8))", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMaxWithExpressions3() throws EvaluatorException, OpenMathException {
		assertEquals(13, Evaluator.getNumberResult("max(max(12,max(13,-13)), max(5, 8))", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testMaxWithPointNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals(1.3, Evaluator.getNumberResult("max(1.0, 1.3)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMaxWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("max(a, a)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMaxWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("max(2)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMaxWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("max(1, 23, 4)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testMaxWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("max('[var=j]', '[var=j]')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testMaxWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("max('[pos=42]', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
	}
}