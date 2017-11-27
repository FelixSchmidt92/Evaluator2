package de.uni_due.s3.evaluator2.core.integration.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsRationalNumber extends TestIntegration {

	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		OMOBJ zero = new OMOBJ();
		OMOBJ mTwelve = new OMOBJ();
		OMOBJ twoHundred = new OMOBJ();
		zero.setOMI(OMCreator.createOMI(0));
		mTwelve.setOMI(OMCreator.createOMI(-12));
		twoHundred.setOMI(OMCreator.createOMI(200));

		exerVar.put("a", twoHundred);
		exerVar.put("b", zero);
		exerVar.put("c", mTwelve);

		fillIn.put(1, twoHundred);
		fillIn.put(2, zero);
		fillIn.put(3, mTwelve);
	}

	@Test
	public void testIsRationalNumber1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('10')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumber2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('1234567890')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumber3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('-101230')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumber4() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRationalNumber('12$56&')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumber5() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRationalNumber('&!§$%&/()123asdf')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumber6() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRationalNumber('abcde')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumber7() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRationalNumber([var=E])", exerVar, fillIn));
	}
	
	@Test
	public void testIsRationalNumber8() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRationalNumber([var=PI])", exerVar, fillIn));
	}
	
	@Test
	public void testIsRationalNumberWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('[pos=1]')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumberWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('[pos=2]')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumberWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('[pos=3]')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumberWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('[var=a]')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumberWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('[var=b]')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumberWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRationalNumber('[var=c]')", exerVar, fillIn));
	}

	@Test
	public void testIsRationalNumberWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRationalNumber(a)", exerVar, fillIn));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsRationalNumberWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isRationalNumber(3/2 , 0)", exerVar, fillIn);
	}
	

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsRationalNumberWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isRationalNumber(3/2, 1/3, 3/1)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIsRationalNumberWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isRationalNumber('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIsRationalNumberWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isRationalNumber('[pos=42]')", exerVar, fillIn);
	}
}
