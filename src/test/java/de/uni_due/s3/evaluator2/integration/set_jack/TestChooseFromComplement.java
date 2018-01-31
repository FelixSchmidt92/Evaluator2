package de.uni_due.s3.evaluator2.integration.set_jack;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestChooseFromComplement extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	private static ArrayList<OMOBJ> results = new ArrayList<OMOBJ>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ hallo = new OMOBJ();
		OMOBJ c = new OMOBJ();
		c.setOMV(OMCreator.createOMV("c"));
		hallo.setOMSTR(OMCreator.createOMSTR("hallo"));

		fillIn.put(1, c);
		fillIn.put(2, hallo);

		exerVar.put("a", c);
		exerVar.put("b", hallo);

		OMOBJ a = new OMOBJ();
		OMOBJ b = new OMOBJ();
		a.setOMV(OMCreator.createOMV("a"));
		b.setOMV(OMCreator.createOMV("b"));

		results.add(a);
		results.add(b);
		results.add(c);
	}

	@Test
	public void testChooseFromComplement1() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("c"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{a;b}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplement2() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("h"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{h;10;m}','{m;10}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplement3() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("a"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{a}','{b;c}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplement4() throws EvaluatorException, OpenMathException {
		assertTrue(results.contains(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplement5() throws EvaluatorException, OpenMathException {
		assertTrue(results.contains(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplement6() throws EvaluatorException, OpenMathException {
		assertTrue(results.contains(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplementWithInput1() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("c"));
		assertTrue(
				expected.equals(Evaluator.evaluate("chooseFromComplement('{a;b;[pos=1]}','{a;b}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplementWithInput2() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR("hallo"));
		assertTrue(expected
				.equals(Evaluator.evaluate("chooseFromComplement('{[pos=2];10;m}','{m;10}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplementWithVariables1() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("c"));
		assertTrue(
				expected.equals(Evaluator.evaluate("chooseFromComplement('{a;b;[var=a]}','{a;b}')", exerVar, fillIn)));
	}

	@Test
	public void testChooseFromComplementWithVariables2() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR("hallo"));
		assertTrue(expected
				.equals(Evaluator.evaluate("chooseFromComplement('{[var=b];10;m}','{m;10}')", exerVar, fillIn)));
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testChooseFromComplementWithSameElements() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('{a;b;1.5}','{a;b;1.5}')", exerVar, fillIn);
	}

	@Test
	public void testChooseFromComplementWithInputCharacter() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("b"));
		assertTrue(expected
				.equals(Evaluator.evaluate("chooseFromComplement('{a;b}', a)", exerVar, fillIn)));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('{a;b;c;d}')", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('{a;b;c;d}', '{a;b}', '{c}')", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testChooseFromComplementWithMissingExcerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('[var=j]', '{a}')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testChooseFromComplementWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('[pos=42]', '{a}')", exerVar, fillIn);
	}

}
