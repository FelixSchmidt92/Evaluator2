package de.uni_due.s3.evaluator2.core.function.relation1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqual extends TestFunctionAbstract {

	private static Function func = new Equal();

	@Test
	public void testEqualsOMI1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMF(10.0));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsOMI2() throws OpenMathException, EvaluatorException {

		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(120976));
		args.add(OMCreator.createOMI(12));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEqualsOMF1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMF(0.546363));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsOMF2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(12.3045));
		args.add(OMCreator.createOMF(12.3044));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEqualsOMS1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.ARITH1_PLUS);
		args.add(OMSymbol.ARITH1_PLUS);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsOMS2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.ARITH1_MINUS);
		args.add(OMSymbol.ARITH1_PLUS);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEqualsOMA1() throws OpenMathException, EvaluatorException {
		ArrayList<Object> matrixRow1 = new ArrayList<Object>();

		matrixRow1.add(OMCreator.createOMI(10));
		matrixRow1.add(OMCreator.createOMI(-10));
		matrixRow1.add(OMCreator.createOMI(30));

		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixRow1));
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixRow1));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsOMA2() throws OpenMathException, EvaluatorException {
		ArrayList<Object> matrixRow1 = new ArrayList<Object>();

		matrixRow1.add(OMCreator.createOMI(10));
		matrixRow1.add(OMCreator.createOMI(-10));
		matrixRow1.add(OMCreator.createOMI(30));

		ArrayList<Object> matrixRow2 = new ArrayList<Object>();

		matrixRow2.add(OMCreator.createOMI(10));
		matrixRow2.add(OMCreator.createOMI(20));
		matrixRow2.add(OMCreator.createOMI(30));

		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixRow1));
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixRow2));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEqualsOMSTR1() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsOMSTR2() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("tes t"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEqualsIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equal(10,10)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualsIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10=10", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualsIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("10==10", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualsSageSyntax() throws EvaluatorException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(10));
		assertEquals("10 == 10", func.getPartialSageSyntax(args));
	}

}