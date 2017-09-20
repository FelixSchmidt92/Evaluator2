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

public class TestGetFromOrderedSet extends TestFunctionAbstract {

	private static Function func = new GetFromOrderedSet();

	@Test
	public void testGetFromOrderedSet1() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMI(1));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.123));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(3.123), result);
	}

	@Test
	public void testGetFromOrderedSet2() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMSTR("Hallo"));
		set.add(OMCreator.createOMSTR("Hello"));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Hallo"), result);
	}

	@Test
	public void testGetFromOrderedSet3() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMF(3.343));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.243));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(20), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedSetWithWrongArguments1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1)); // wrong
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetFromOrderedSetWithWrongArguments2() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set)); // wrong
		func.evaluate(args);
		fail();
	}

	@Test
	public void testGetFromOrderedSetIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet(0, {1;2})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetFromOrderedSetIntegrationWrongArgsInSet() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({1;'Test'},1)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedSetIntegrationWithOutOfBounds() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet(2, {1})", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetFromOrderedSetIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({matrix(matrixrow(1,2)); vector(1,2)},1)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedSetWithZeroArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet(0, {})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithLessArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithMoreArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({}, 0, 4)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
