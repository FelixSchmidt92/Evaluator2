package de.uni_due.s3.evaluator2.integration.arith_jack;

import static org.junit.Assert.assertEquals;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.*;
import de.uni_due.s3.evaluator2.exceptions.parser.*;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMin extends TestIntegration {

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
	public void testMin1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("min(1, 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMin2() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("min(3, 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMin3() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("min(1, 1)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMin4() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("min(0, 0)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMin5() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("min(-2, 0)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMin6() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("min(-2, 2)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test
	public void testMinWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("min([var=a], [var=b])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMinWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("min([var=a], [var=c])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMinWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(1, Evaluator.getNumberResult("min([pos=1], [pos=2])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMinWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(-1, Evaluator.getNumberResult("min([pos=1], [pos=3])", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMinWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(-12, Evaluator.getNumberResult("min(min(-12,2), 4)", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMinWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(-12,
				Evaluator.getNumberResult("min(min(-12,2), min(5, 8))", exerciseVariableMap, fillInVariableMap),
				0.0);
	}

	@Test
	public void testMinWithExpressions3() throws EvaluatorException, OpenMathException {
		assertEquals(-13, Evaluator.getNumberResult("min(min(-12,min(13,-13)), min(5, 8))", exerciseVariableMap,
				fillInVariableMap), 0.0);
	}

	@Test
	public void testMinWithPointNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals(1.0, Evaluator.getNumberResult("min(1.0, 1.3)", exerciseVariableMap, fillInVariableMap), 0.0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMinWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("min(a, a)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMinWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("min(2)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMinWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("min(1, 23, 4)", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testMinWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("min('[var=j]', '[var=j]')", exerciseVariableMap, fillInVariableMap);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testMinWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("min('[pos=42]', '[pos=42]')", exerciseVariableMap, fillInVariableMap);
	}
}