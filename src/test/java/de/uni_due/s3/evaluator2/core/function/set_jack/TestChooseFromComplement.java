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
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestChooseFromComplement extends TestFunctionAbstract {

	private static Function func = new ChooseFromComplement();

	@Test
	public void testChooseFromComplement1() throws OpenMathException, EvaluatorException {
		List<Object> set1 = new ArrayList<Object>();
		set1.add(OMCreator.createOMI(1));
		set1.add(OMCreator.createOMI(20));
		set1.add(OMCreator.createOMF(3.123));

		List<Object> set2 = new ArrayList<Object>();
		set2.add(OMCreator.createOMI(1));
		set2.add(OMCreator.createOMI(20));

		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(3.123), result);
	}

	@Test
	public void testChooseFromComplement2() throws OpenMathException, EvaluatorException {
		List<Object> set1 = new ArrayList<Object>();
		set1.add(OMCreator.createOMI(1));
		set1.add(OMCreator.createOMI(20));
		set1.add(OMCreator.createOMF(3.123));
		List<Object> set2 = new ArrayList<Object>();
		set2.add(OMCreator.createOMSTR("Test"));
		set2.add(OMCreator.createOMSTR("Hallo"));
		set2.add(OMCreator.createOMSTR("Hello"));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set1));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set2));
		Object result = func.evaluate(args);
		assertTrue(result.equals(OMCreator.createOMI(1)) || result.equals(OMCreator.createOMI(20))
				|| result.equals(OMCreator.createOMF(3.123)));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testChooseFromComplementWithWrongArguments1() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testChooseFromComplementIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("chooseFromComplement({1;2;3;4},{1;2;3})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(4), result.getOMI());
	}

	@Test
	public void testChooseFromComplementIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("chooseFromComplement({1;'test';vector(1,2,3);4},{1;'test';vector(1,2,3)})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(4), result.getOMI());
	}

	@Test
	public void testChooseFromComplementIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("chooseFromComplement({1;'test';vector(1,2,3);4},{1;'test';4})", null,
				null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> vector = new ArrayList<>();
		vector.add(OMCreator.createOMI(1));
		vector.add(OMCreator.createOMI(2));
		vector.add(OMCreator.createOMI(3));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, vector), result.getOMA());
	}

	@Test
	public void testChooseFromComplementIntegrationArgsInSet() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("chooseFromComplement({1;'Test'},1)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMSTR("Test"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testChooseFromComplementWithZeroArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("chooseFromComplement({}, {2;3})", null, null);
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
	public void testChooseFromComplementWithMoreArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({}, {}, 1)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
