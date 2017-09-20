package de.uni_due.s3.evaluator2.core.function.set_jack;

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
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetFromSet extends TestFunctionAbstract {

	private static Function func = new GetFromSet();

	@Test
	public void testGetFromSet1() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.123));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(20), result);
	}

	@Test
	public void testGetFromSet2() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.123));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Test"), result);
	}

	@Test
	public void testGetFromSet3() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromSetWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromSetWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromSetWithWrongArguments1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1)); // wrong
		func.evaluate(args);
		fail();
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetFromSetWithWrongArguments2() throws OpenMathException, EvaluatorException {
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(20));
		plus.add(OMCreator.createOMI(1));
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set)); // wrong
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testGetFromSetIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromSet(0,{1;2})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test
	public void testGetFromSetIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromSet(1, {1;'Test'})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMSTR("Test"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromSetIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromSet(2, {1})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test
	public void testGetFromSetIntegration4() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromSet(1, {1; matrix(matrixrow(1,2));getFromSet(0, {vector(1,2)})})",
				null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result.getOMA());
	}

	@Test
	public void testGetFromSetIntegration5() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("getFromSet(2, {1; matrix(matrixrow(1,2)); getFromSet(0, {vector(1,2,7.1); 0})})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMF(7.1));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result.getOMA());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromSetWithZeroArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromSet(0, {})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromSetWithLessArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromSet({})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromSetWithMoreArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromSet({}, 0, 4)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
