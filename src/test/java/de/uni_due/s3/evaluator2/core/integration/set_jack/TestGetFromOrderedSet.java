package de.uni_due.s3.evaluator2.core.integration.set_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetFromOrderedSet extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ zero = new OMOBJ();
		OMOBJ mfour = new OMOBJ();
		OMOBJ twenty = new OMOBJ();
		zero.setOMI(OMCreator.createOMI(0));
		mfour.setOMI(OMCreator.createOMI(-4));
		twenty.setOMI(OMCreator.createOMI(20));

		fillIn.put(1, zero);
		fillIn.put(2, mfour);
		fillIn.put(3, twenty);

		exerVar.put("a", zero);
		exerVar.put("b", mfour);
		exerVar.put("c", twenty);
	}

	@Test
	public void testGetFromOrderedSet1() throws OpenMathException, EvaluatorException {
		assertTrue(3 == Evaluator.getNumberResult("getFromOrderedSet('1', '{2;3;5;6}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSet2() throws OpenMathException, EvaluatorException {
		assertTrue(-2.6 == Evaluator.getNumberResult("getFromOrderedSet('0', '{3;5;6;-2.6}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSet3() throws OpenMathException, EvaluatorException {
		assertTrue(0 == Evaluator.getNumberResult("getFromOrderedSet('1', '{-2.6;3;5;0}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSet4() throws OpenMathException, EvaluatorException {
		assertTrue(5 == Evaluator.getNumberResult("getFromOrderedSet('3', '{-2.6;3;5;0}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSet5() throws OpenMathException, EvaluatorException {
		assertTrue(1 == Evaluator.getNumberResult("getFromOrderedSet('0', '{1;1;1;1}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSet6() throws OpenMathException, EvaluatorException {
		assertTrue(1 == Evaluator.getNumberResult("getFromOrderedSet('3', '{1;1;1;1}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithInput1() throws OpenMathException, EvaluatorException {
		assertTrue(20 == Evaluator.getNumberResult("getFromOrderedSet('2', '{[pos=3];4;5}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithInput2() throws OpenMathException, EvaluatorException {
		assertTrue(-4 == Evaluator.getNumberResult("getFromOrderedSet('0', '{[pos=3];4;5;[pos=2]}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithInput3() throws OpenMathException, EvaluatorException {
		assertTrue(-4 == Evaluator.getNumberResult("getFromOrderedSet('[pos=1]', '{[pos=3];4;5;[pos=2]}')", exerVar,
				fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithVariables1() throws OpenMathException, EvaluatorException {
		assertTrue(20 == Evaluator.getNumberResult("getFromOrderedSet('2', '{[var=c];4;5}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithVariables2() throws OpenMathException, EvaluatorException {
		assertTrue(-4 == Evaluator.getNumberResult("getFromOrderedSet('0', '{[var=c];4;5;[var=b]}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithVariables3() throws OpenMathException, EvaluatorException {
		assertTrue(-4 == Evaluator.getNumberResult("getFromOrderedSet('[var=a]', '{[var=c];4;5;[var=b]}')", exerVar,
				fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithOneRationalAndOneTextArgument() throws OpenMathException, EvaluatorException {
		assertTrue(4 == Evaluator.getNumberResult("getFromOrderedSet('1', '{2;4;5}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromOrderedSetWithTwoTextArguments() throws OpenMathException, EvaluatorException {
		assertTrue(4 == Evaluator.getNumberResult("getFromOrderedSet('1', '{2;4;5}')", exerVar, fillIn));
	}

	@Test // (expected=FunctionInvalidArgumentTypeException.class) Now works, single chars
			// are recognized as Variables
	public void testGetFromOrderedSetWithWrongInputCharacters() throws OpenMathException, EvaluatorException {
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMSTR("b")),
				Evaluator.evaluate("getFromOrderedSet('1', '{a;b;c;d}')", exerVar, fillIn));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedSetWithWrongInputCharacterIndex() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("getFromOrderedSet('a', '{1;2;3;4}')", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedSetWithWrongInputDoubleIndex() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("getFromOrderedSet('5.5', '{1;2;3;4}')", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithOneArgument() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("getFromOrderedSet('7')", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithThreeArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("getFromOrderedSet('4', '{3;1;-1}', '{12;-3}')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testGetFromOrderedSetWithMissingInput() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("getFromOrderedSet('2', '{[pos=42];4;5}')", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testGetFromOrderedSetWithMissingExerciseVariable() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("getFromOrderedSet('2', '{[var=j];4;5}')", exerVar, fillIn);
	}
}
