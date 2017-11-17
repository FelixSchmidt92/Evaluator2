package de.uni_due.s3.evaluator2.core.function.set_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEqualSet extends TestFunctionAbstract {

	private static Function func = new EqualSet();

	@Test
	public void testEqualSet1() throws OpenMathException, EvaluatorException {
		List<Object> set1 = new ArrayList<Object>();
		set1.add(OMCreator.createOMI(1));
		set1.add(OMCreator.createOMI(20));
		set1.add(OMCreator.createOMF(3.123));

		List<Object> set2 = new ArrayList<Object>();
		set2.add(OMCreator.createOMI(1));
		set2.add(OMCreator.createOMI(20));

		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set2));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEqualSet2() throws OpenMathException, EvaluatorException {
		List<Object> set1 = new ArrayList<Object>();
		set1.add(OMCreator.createOMI(1));
		set1.add(OMCreator.createOMI(20));
		set1.add(OMCreator.createOMF(3.123));
		List<Object> set2 = new ArrayList<Object>();
		set2.add(OMCreator.createOMSTR("Test"));
		set2.add(OMCreator.createOMSTR("Hallo"));
		set2.add(OMCreator.createOMSTR("Hello"));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set2));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}
	
	@Test
	public void testEqualSet3() throws OpenMathException, EvaluatorException {
		List<Object> set1 = new ArrayList<Object>();
		set1.add(OMCreator.createOMI(1));
		set1.add(OMCreator.createOMI(20));
		set1.add(OMCreator.createOMF(3.123));

		List<Object> set2 = new ArrayList<Object>();
		set2.add(OMCreator.createOMI(1));
		set2.add(OMCreator.createOMI(20));
		set2.add(OMCreator.createOMF(3.123));

		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set2));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}
	
	@Test
	public void testEqualSet4() throws OpenMathException, EvaluatorException {
		List<Object> set1 = new ArrayList<Object>();
		set1.add(OMCreator.createOMI(1));
		set1.add(OMCreator.createOMI(20));
		set1.add(OMCreator.createOMF(3.123));

		List<Object> set2 = new ArrayList<Object>();
		set2.add(OMCreator.createOMI(20));
		set2.add(OMCreator.createOMI(1));
		set2.add(OMCreator.createOMF(3.123));

		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set2));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualSetWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, set));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualSetWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
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

	@Test
	public void testEqualSetIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({1;2;3;4},{1;2;3})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test
	public void testEqualSetIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser
				.parse("equalSet({1;'test';vector(1,2,3)},{1;'test';vector(1,2,3)})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualSetIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({1;'test';vector(1,2,3);4},{1;'test';4})", null,
				null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test
	public void testEqualSetIntegrationArgsNotInSet1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({1;'Test'},1)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testEqualSetIntegrationArgsNotInSet2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({1},1)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualSetWithZeroArgs1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({}, {2;3})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test
	public void testEqualSetWithZeroArgs2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({}, {})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualSetWithLessArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({})", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualSetWithMoreArgs() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("equalSet({}, {}, 1)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}
