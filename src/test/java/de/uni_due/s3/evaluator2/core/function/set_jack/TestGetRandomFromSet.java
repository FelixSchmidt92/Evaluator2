package de.uni_due.s3.evaluator2.core.function.set_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetRandomFromSet extends TestFunctionAbstract {

	private static Function func = new GetRandomFromSet();

	@Test
	public void testGetRandomFromSet1() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();

		OMF omf = OMCreator.createOMF(1.1);
		OMI omi = OMCreator.createOMI(10);
		OMSTR omstr = OMCreator.createOMSTR("test");
		set.add(omstr);
		set.add(omi);
		set.add(omf);

		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		Object result = func.evaluate(args);
		assertTrue(result.equals(omf) || result.equals(omi) || result.equals(omstr));
	}

	@Test
	public void testGetRandomFromSet2() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();

		OMF omf = OMCreator.createOMF(1.1);
		OMI omi = OMCreator.createOMI(10);
		OMSTR omstr = OMCreator.createOMSTR("test");
		set.add(omstr);
		set.add(omi);
		set.add(omf);

		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		Object result = func.evaluate(args);
		assertTrue(result.equals(omf) || result.equals(omi) || result.equals(omstr));

	}

	@Test
	public void testGetRandomFromSet3() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();

		OMA oma = OMCreator.createOMA(OMSymbol.ARITH1_ABS, new ArrayList<Object>());
		set.add(oma);
		set.add(oma);
		set.add(oma);

		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		Object result = func.evaluate(args);
		assertTrue(result.equals(oma));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromSetWithLessThanMinParam() throws OpenMathException, EvaluatorException {

		List<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromSetWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
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

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromSetWithWrongArguments1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test
	public void testGetRandomFromSetIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromSet({1})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetRandomFromSetWithEmptyArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromSet({})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromSetWithLessArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromSet()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetRandomFromSetWithMoreArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getRandomFromSet({}, 0, 4)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
