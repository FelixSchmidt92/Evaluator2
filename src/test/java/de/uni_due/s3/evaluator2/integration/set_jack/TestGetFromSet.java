package de.uni_due.s3.evaluator2.integration.set_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetFromSet extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ zero = new OMOBJ();
		OMOBJ two = new OMOBJ();
		OMOBJ mtwo = new OMOBJ();
		OMOBJ e = new OMOBJ();
		e.setOMV(OMCreator.createOMV("e"));
		zero.setOMI(OMCreator.createOMI(0));
		two.setOMI(OMCreator.createOMI(2));
		mtwo.setOMI(OMCreator.createOMI(-2));

		fillIn.put(1, zero);
		fillIn.put(2, two);
		fillIn.put(3, mtwo);
		fillIn.put(4, e);

		exerVar.put("a", zero);
		exerVar.put("b", two);
		exerVar.put("c", mtwo);
		exerVar.put("d", e);
	}

	@Test
	public void testGetFromSet1() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("getFromSet('1', '{1;2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSet2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("'c' == getFromSet('2', '{a;b;c}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromSet3() throws EvaluatorException, OpenMathException {
		assertEquals(42, Evaluator.getNumberResult("getFromSet('3', {a;12;'lalilu';42;-1})", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSet4() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult(" '}' ==getFromSet('1', {2;'}';3})", exerVar, fillIn));
	}

	@Test
	public void testGetFromSet5() throws EvaluatorException, OpenMathException {
		assertEquals(3, Evaluator.getNumberResult("getFromSet('2', {2;'}';3})", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSet6() throws EvaluatorException, OpenMathException {
		assertEquals(2, Evaluator.getNumberResult("getFromSet('1', '{1;2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSet7() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("'c' == getFromSet('2', '{a;b;c}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromSet8() throws EvaluatorException, OpenMathException {
		assertEquals(42, Evaluator.getNumberResult("getFromSet('3', {a;12;'lalilu';42;-1})", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSet9() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR("1,3,2,7"));
		assertEquals(expected, (Evaluator.evaluate("getFromSet('0', {'1,3,2,7'})", exerVar, fillIn)));
	}

	@Test
	public void testGetFromSetWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("getFromSet('0', '{[pos=1];2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSetWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("getFromSet('[pos=1]', '{[pos=3];2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSetWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(true,
				Evaluator.getBooleanResult("'e' == getFromSet('[pos=2]', '{a;b;[pos=4]}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromSetWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("getFromSet('0', '{[var=a];2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSetWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(-2, Evaluator.getNumberResult("getFromSet('[var=a]', '{[var=c];2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSetWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(true,
				Evaluator.getBooleanResult("'e' == getFromSet('[var=b]', '{a;b;[var=d]}')", exerVar, fillIn));
	}

	@Test
	public void testGetFromSetWithPointNumber1() throws EvaluatorException, OpenMathException {
		assertEquals(3.2, Evaluator.getNumberResult("getFromSet('0', '{3.2;2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSetWithPointNumber2() throws EvaluatorException, OpenMathException {
		assertEquals(-2.2, Evaluator.getNumberResult("getFromSet('[var=a]', '{-2.2;2;3}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSetWithPointNumber3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("'e.a' == getFromSet('2', {a;b;'e.a'})", exerVar, fillIn));
	}

	@Test
	public void testGetFromSetWithOneRationalAndOneTextArgument() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("getFromSet('1', '{2;4;5}')", exerVar, fillIn), 0);
	}

	@Test
	public void testGetFromSetWithTwoTextArguments() throws EvaluatorException, OpenMathException {
		assertEquals(4, Evaluator.getNumberResult("getFromSet('1', '{2;4;5}')", exerVar, fillIn), 0);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromSetWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getFromSet('3')", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromSetWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getFromSet('4', '{3;1;-7}', '{12;-12}')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testGetFromSetWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getFromSet('2', '{[pos=42];4;5}')", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testGetFromSetWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("getFromSet('2', '{[var=j];4;5}')", exerVar, fillIn);
	}
}
