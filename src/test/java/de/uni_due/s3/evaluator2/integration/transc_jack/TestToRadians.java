package de.uni_due.s3.evaluator2.integration.transc_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestToRadians extends TestIntegration {

	private static double PI = Math.PI;

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ to180 = new OMOBJ();
		OMOBJ zero = new OMOBJ();
		to180.setOMI(OMCreator.createOMI(180));
		zero.setOMI(OMCreator.createOMI(0));

		fillIn.put(1, to180);
		fillIn.put(2, zero);

		exerVar.put("a", to180);
		exerVar.put("b", zero);
	}

	@Test
	public void testToRadians1() throws EvaluatorException, OpenMathException {
		assertEquals(PI, Evaluator.getNumberResult("toRadians(180)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadians2() throws EvaluatorException, OpenMathException {
		assertEquals(2 * PI, Evaluator.getNumberResult("toRadians(360)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadians3() throws EvaluatorException, OpenMathException {
		assertEquals(3 * PI, Evaluator.getNumberResult("toRadians(540)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadians4() throws EvaluatorException, OpenMathException {
		assertEquals(PI / 2, Evaluator.getNumberResult("toRadians(90)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadians5() throws EvaluatorException, OpenMathException {
		assertEquals(-PI, Evaluator.getNumberResult("toRadians(-180)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadians6() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("toRadians(0)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadiansWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(PI, Evaluator.getNumberResult("toRadians('[pos=1]')", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadiansWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("toRadians('[pos=2]')", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadiansWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(PI, Evaluator.getNumberResult("toRadians('[var=a]')", exerVar, fillIn), 0);

	}

	@Test
	public void testToRadiansWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("toRadians('[var=b]')", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadiansWithPointNumbers1() throws EvaluatorException, OpenMathException {
		assertEquals((PI * .2) / 180, Evaluator.getNumberResult("toRadians(0.2)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadiansWithPointNumbers2() throws EvaluatorException, OpenMathException {
		assertEquals((-PI * .1) / 180, Evaluator.getNumberResult("toRadians(-0.1)", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadiansWithExpressions1() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("toRadians(toRadians(0))", exerVar, fillIn), 0);
	}

	@Test
	public void testToRadiansWithExpressions2() throws EvaluatorException, OpenMathException {
		assertEquals(0, Evaluator.getNumberResult("toRadians(toRadians(toRadians(0)))", exerVar, fillIn), 0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testToRadiansWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("toRadians(a)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToRadiansWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("toRadians(180, 90)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToRadiansWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("toRadians(45, 90, 180)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testToRadiansWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("toRadians('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testToRadiansWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("toRadians('[pos=42]')", exerVar, fillIn);
	}
}