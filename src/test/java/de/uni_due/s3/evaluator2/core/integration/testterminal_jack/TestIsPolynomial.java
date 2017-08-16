package de.uni_due.s3.evaluator2.core.integration.testterminal_jack;

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

public class TestIsPolynomial extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ x = new OMOBJ();
		OMOBJ y = new OMOBJ();
		x.setOMV(OMCreator.createOMV("x"));
		y.setOMV(OMCreator.createOMV("y"));

		fillIn.put(1, x);
		fillIn.put(2, y);

		exerVar.put("a", x);
		exerVar.put("b", y);
	}

	@Test
	public void testIsPolynomial1() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('x^2+x+1','x')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial2() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('y^2+3*y+4','y')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial3() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('3*a^5+2*a^3-a','a')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial4() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('m^3+2*m-2','m')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial5() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('x^2+x+1','y')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial6() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('x','x')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial7() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('sin(x)+ y^3', 'y')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial8() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('e^(x) + y^3', 'y')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial9() throws OpenMathException, EvaluatorException {
		assertEquals(false, Evaluator.getBooleanResult("isPolynomial('sin(x)+ y^3', 'x')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomial10() throws OpenMathException, EvaluatorException {
		assertEquals(false, Evaluator.getBooleanResult("isPolynomial('e^(x) + y^3', 'x')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomialWithInput() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('x^2+x+1','[pos=1]')", exerVar, fillIn));
	}

	@Test
	public void testIsPolynomialWithVariables() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('x^2+x+1','[var=a]')", exerVar, fillIn));
	}

	@Test // (expected=AssertionError.class)
	public void testIsPolynomialWithNumberAsSecondArgument1() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('sin(x)', '1')", exerVar, fillIn));// Look if 1 is
																										// Polynomial
	}

	@Test // (expected=AssertionError.class)
	public void testIsPolynomialWithNumberAsSecondArgument2() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('e^(x)', '0')", exerVar, fillIn)); // Look if 0 is
																										// Polynomial
	}

	@Test // (expected=AssertionError.class)
	public void testIsPolynomialWithNumberAsSecondArgument3() throws OpenMathException, EvaluatorException {
		assertEquals(true, Evaluator.getBooleanResult("isPolynomial('x^2+x+1','2')", exerVar, fillIn));// Look if 2 is
																										// Polynomial
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsPolynomialWithEmptyStringArguments() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isPolynomial('', '')", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsPolynomialWithEmptyArgument() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isPolynomial()", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIsPolynomialWithoutExerciseVariable() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isPolynomial('[var=j]', '[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIsPolynomialWithoutInput() throws OpenMathException, EvaluatorException {
		Evaluator.getBooleanResult("isPolynomial('[pos=42]', '[pos=42]')", exerVar, fillIn);
	}
}