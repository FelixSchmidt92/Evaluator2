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

public class TestIsRealNumber extends TestIntegration {

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
	public void testIsRealNumber1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('10')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumber2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('1234567890')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumber3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('-101230')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumber4() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRealNumber('12$56&')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumber5() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRealNumber('&!§$%&/()123asdf')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumber6() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRealNumber('abcde')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumberWithInput1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('[pos=1]')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumberWithInput2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('[pos=2]')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumberWithInput3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('[pos=3]')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumberWithVariables1() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('[var=a]')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumberWithVariables2() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('[var=b]')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumberWithVariables3() throws EvaluatorException, OpenMathException {
		assertEquals(true, Evaluator.getBooleanResult("isRealNumber('[var=c]')", exerVar, fillIn));
	}

	@Test
	public void testIsRealNumberWithWrongInputCharacter() throws EvaluatorException, OpenMathException {
		assertEquals(false, Evaluator.getBooleanResult("isRealNumber(a)", exerVar, fillIn));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsRealNumberWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isRealNumber(3/2 , 0)", exerVar, fillIn);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIsRealNumberWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isRealNumber(3/2, 1/3, 3/1)", exerVar, fillIn);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testIsRealNumberWithMissingExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isRealNumber('[var=j]')", exerVar, fillIn);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testIsRealNumberWithMissingInput() throws EvaluatorException, OpenMathException {
		Evaluator.getBooleanResult("isNumber('[pos=42]')", exerVar, fillIn);
	}
}
