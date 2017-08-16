package de.uni_due.s3.evaluator2.core.integration.rounding1;

import static org.junit.Assert.assertEquals;

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

public class TestCeil extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ fourFive = new OMOBJ();
		fourFive.setOMF(OMCreator.createOMF(4.5));

		fillIn.put(1, fourFive);

		exerVar.put("a", fourFive);
	}

	@Test
	public void testCeil1() throws OpenMathException, EvaluatorException {
		assertEquals(1, Evaluator.getNumberResult("ceil('0.5')", exerVar, fillIn), 0);
	}

	@Test
	public void testCeil2() throws OpenMathException, EvaluatorException {
		assertEquals(-3, Evaluator.getNumberResult("ceil(-3.000001)", exerVar, fillIn), 0);
	}

	@Test
	public void testCeil3() throws OpenMathException, EvaluatorException {
		assertEquals(1, Evaluator.getNumberResult("ceil(1)", exerVar, fillIn), 0);
	}

	@Test
	public void testCeil4() throws OpenMathException, EvaluatorException {
		assertEquals(0, Evaluator.getNumberResult("ceil(0)", exerVar, fillIn), 0);
	}

	@Test
	public void testCeilWithInput1() throws OpenMathException, EvaluatorException {
		assertEquals(1, Evaluator.getNumberResult("ceil('0.5')", exerVar, fillIn), 0);
	}

	@Test
	public void testCeilWithInput2() throws OpenMathException, EvaluatorException {
		assertEquals(5, Evaluator.getNumberResult("ceil('[pos=1]')", exerVar, fillIn), 0);
	}

	@Test
	public void testCeilWithVariables1() throws OpenMathException, EvaluatorException {
		assertEquals(1, Evaluator.getNumberResult("ceil('0.5')", exerVar, fillIn), 0);
	}

	@Test
	public void testCeilWithVariables2() throws OpenMathException, EvaluatorException {
		assertEquals(5, Evaluator.getNumberResult("ceil('[var=a]')", exerVar, fillIn), 0);
	}

	@Test
	public void testCeilWithExpressions1() throws OpenMathException, EvaluatorException {
		assertEquals(-2, Evaluator.getNumberResult("ceil(ceil(-2.2))", exerVar, fillIn), 0);

	}

	@Test
	public void testCeilWithExpressions2() throws OpenMathException, EvaluatorException {
		assertEquals(2, Evaluator.getNumberResult("ceil(ceil(ceil(1.11)))", exerVar, fillIn), 0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class) // (expected=InvalidEvaluatorFunctionArgumentException.class)
	public void testCeilWithWrongInputCharacter() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("ceil(a)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCeilWithTwoArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("ceil(2.2, 3.3)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCeilWithThreeArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("ceil(2.2, 3.3, -1.1)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testCeilWithMissingExcerciseVariable() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("ceil('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testCeilWithMissingInput() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("ceil('[pos=42]')", exerVar, fillIn);
	}

}
