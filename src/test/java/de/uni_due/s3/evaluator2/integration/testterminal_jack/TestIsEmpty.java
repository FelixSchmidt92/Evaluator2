package de.uni_due.s3.evaluator2.integration.testterminal_jack;

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

public class TestIsEmpty extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ one = new OMOBJ();
		one.setOMI(OMCreator.createOMI(1));
		fillIn.put(2, one);
		exerVar.put("b", one);
	}

	@Test
	public void testIsEmpty1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isEmpty('')", exerVar, fillIn));
	}

	@Test
	public void testIsEmpty2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isEmpty('   ')", exerVar, fillIn));
	}

	@Test
	public void testIsEmpty3() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isEmpty('a')", exerVar, fillIn));
	}

	@Test
	public void testIsEmpty4() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isEmpty('5')", exerVar, fillIn));
	}

	@Test
	public void testIsEmptyWithInput() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isEmpty('[pos=2]')", exerVar, fillIn));
	}

	@Test
	public void testIsEmptyWithVariables() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isEmpty('[var=b]')", exerVar, fillIn));
	}

	@Test
	public void testIsEmptyWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isEmpty(a)", exerVar, fillIn));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsEmptyWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isEmpty('a', 0)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsEmptyWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isEmpty('string', 1, 3)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIsEmptyWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isEmpty('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIsEmptyWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isEmpty('[pos=42]')", exerVar, fillIn);
	}
}
