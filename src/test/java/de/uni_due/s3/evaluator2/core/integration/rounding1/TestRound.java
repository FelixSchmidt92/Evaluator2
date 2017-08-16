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

public class TestRound extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ nineThree = new OMOBJ();
		OMOBJ threeFive = new OMOBJ();
		nineThree.setOMF(OMCreator.createOMF(93.49999));
		threeFive.setOMF(OMCreator.createOMF(3.5));

		fillIn.put(4, threeFive);
		fillIn.put(93, nineThree);

		exerVar.put("a", threeFive);
		exerVar.put("b", nineThree);
	}

	@Test
	public void testRound1() throws OpenMathException, EvaluatorException {
		assertEquals(6, Evaluator.getNumberResult("round(6.3)", exerVar, fillIn), 0);
	}

	@Test
	public void testRound2() throws OpenMathException, EvaluatorException {
		assertEquals(93, Evaluator.getNumberResult("round(93.49999)", exerVar, fillIn), 0);
	}

	@Test
	public void testRound3() throws OpenMathException, EvaluatorException {
		assertEquals(94, Evaluator.getNumberResult("round(93.50001)", exerVar, fillIn), 0);
	}

	@Test
	public void testRound4() throws OpenMathException, EvaluatorException {
		assertEquals(-93, Evaluator.getNumberResult("round(-93.49999)", exerVar, fillIn), 0);
	}

	@Test
	public void testRound5() throws OpenMathException, EvaluatorException {
		assertEquals(-94, Evaluator.getNumberResult("round(-93.50001)", exerVar, fillIn), 0);
	}

	@Test
	public void testRound6() throws OpenMathException, EvaluatorException {
		assertEquals(4, Evaluator.getNumberResult("round(3.5)", exerVar, fillIn), 0);
	}

	@Test
	public void testRound7() throws OpenMathException, EvaluatorException {
		assertEquals(3, Evaluator.getNumberResult("round(2.5)", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithInput1() throws OpenMathException, EvaluatorException {
		assertEquals(4, Evaluator.getNumberResult("round([pos=4])", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithInput2() throws OpenMathException, EvaluatorException {
		assertEquals(93, Evaluator.getNumberResult("round([pos=93])", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithVariable1() throws OpenMathException, EvaluatorException {
		assertEquals(4, Evaluator.getNumberResult("round([var=a])", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithVariable2() throws OpenMathException, EvaluatorException {
		assertEquals(93, Evaluator.getNumberResult("round([var=b])", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithPointNumbers1() throws OpenMathException, EvaluatorException {
		assertEquals(0, Evaluator.getNumberResult("round(0.2)", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithPointNumbers2() throws OpenMathException, EvaluatorException {
		assertEquals(0, Evaluator.getNumberResult("round(-0.2)", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithExpressions1() throws OpenMathException, EvaluatorException {
		assertEquals(-2, Evaluator.getNumberResult("round(round(-2.2))", exerVar, fillIn), 0);
	}

	@Test
	public void testRoundWithExpressions2() throws OpenMathException, EvaluatorException {
		assertEquals(2, Evaluator.getNumberResult("round(round(round(2.1)))", exerVar, fillIn), 0);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testRoundWithWrongInputCharacter() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("round(a)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRoundWithTwoArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("round(1.2, 1.3)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRoundWithThreeArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("round(2.1, 2.2, 2.3)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testRoundWithMissingExerciseVariable() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("round('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testRoundWithMissingInput() throws OpenMathException, EvaluatorException {
		Evaluator.getNumberResult("round('[pos=42]')", exerVar, fillIn);
	}
}