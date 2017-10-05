package de.uni_due.s3.evaluator2.core.integration.eval_jack;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvalPolynomial extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIns = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVars = new HashMap<>();

	@BeforeClass
	public static void beforeTest() throws OpenMathException {
		fillIns.put(1, OMCreator.createOMOBJ(OMCreator.createOMI(0)));
		fillIns.put(2, OMCreator.createOMOBJ(OMCreator.createOMI(1)));

		exerVars.put("a", OMCreator.createOMOBJ(OMCreator.createOMI(0)));
		exerVars.put("b", OMCreator.createOMOBJ(OMCreator.createOMI(1)));
	}

	@Test
	public void testEvalPolynomial1() throws EvaluatorException, OpenMathException {
		assertTrue(18 == Evaluator.getNumberResult("evalpolynomial('3*x^2+4*x-2','2')", null, null));
	}

	@Test
	public void testEvalPolynomial2() throws EvaluatorException, OpenMathException {
		assertTrue(3 == Evaluator.getNumberResult("evalpolynomial('y^2+2*y','1')", null, null));
	}

	@Test
	public void testEvalPolynomial3() throws EvaluatorException, OpenMathException {
		assertTrue(10 == Evaluator.getNumberResult("evalpolynomial('2*a+8','1')", null, null));
	}

	@Test
	public void testEvalPolynomial4() throws EvaluatorException, OpenMathException {
		assertTrue(4 == Evaluator.getNumberResult("evalpolynomial('b^2-4*b+8','2')", null, null));
	}

	@Test
	public void testEvalPolynomial5() throws EvaluatorException, OpenMathException {
		assertTrue(4 == Evaluator.getNumberResult("evalpolynomial('b^2-4*b+8','2')", null, null));
	}

	@Test
	public void testEvalPolynomialWithInput() throws EvaluatorException, OpenMathException {
		assertTrue(3 == Evaluator.getNumberResult("evalpolynomial('y^2+2*y','[pos=2]')", null, fillIns));
	}

	@Test
	public void testEvalPolynomialWithVariables() throws EvaluatorException, OpenMathException {
		assertTrue(10 == Evaluator.getNumberResult("evalpolynomial('[var=a]*a+2*a+8','1')", exerVars, null));
	}

	@Test
	public void testEvalPolynomialWithDifferentVariableNamesInPolynom1() throws EvaluatorException, OpenMathException {
		assertTrue(2 == Evaluator.getNumberResult("evalpolynomial('(x)^2+(x)^4','1')", null, null));
	}

	@Test
	public void testEvalPolynomialWithDifferentVariableNamesInPolynom2() throws EvaluatorException, OpenMathException {
		assertTrue(3 == Evaluator.getNumberResult("evalpolynomial('(a)^2+(b)^4+(c)^5','1')", null, null));
	}

	@Test
	public void testEvalPolynomialWithDifferentVariableNamesInPolynom3() throws EvaluatorException, OpenMathException {
		assertTrue(3 == Evaluator.getNumberResult("evalpolynomial('(c)^2+(c)^4+(c)^5','1')", null, null));
	}

	@Test(expected = CasEvaluationException.class)
	public void testEvalPolynomialWithDifferentVariableNamesInPolynom4() throws EvaluatorException, OpenMathException {
		assertTrue(4 == Evaluator.getNumberResult("evalpolynomial('(ü)^2', '2')", null, null));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEvalPolynomialWithCharacterAsSecondArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("evalpolynomial('a+2*a+8','a')", null, null);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEvalPolynomialWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("evalpolynomial(a, a)", null, null);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEvalPolynomialWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult(("evalpolynomial('', '')"), null, null);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalPolynomialWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult(("evalpolynomial()"), null, null);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testEvalPolynomialWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("evalpolynomial([var=j], '3')", null, fillIns);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testEvalPolynomialWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("evalpolynomial([pos=42], '3')", exerciseVariableMap, null);
	}

}
