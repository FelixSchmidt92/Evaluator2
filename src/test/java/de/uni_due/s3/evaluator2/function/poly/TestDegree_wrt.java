package de.uni_due.s3.evaluator2.function.poly;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.poly.Degree_wrt;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDegree_wrt extends TestFunctionAbstract {

	private Function func = new Degree_wrt();

	@Test
	public void testDegreeWrtWithOneVariable() throws EvaluatorException, OpenMathException {
		List<Object> pow = new ArrayList<Object>();
		pow.add(OMCreator.createOMV("a"));
		pow.add(OMCreator.createOMI(2));
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(1));
		plus.add(OMCreator.createOMA(OMSymbol.ARITH1_POWER, pow));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		args.add(OMCreator.createOMV("a"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testDegreeWrtWithTwoVariable() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(ExpressionParser.parse("1+a^2-b^5", null, null));
		args.add(OMCreator.createOMV("b"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(5), result);
	}

	@Test
	public void testDegreeWrtWithEmptyVariable() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(ExpressionParser.parse("1+a^6-b^5", null, null));
		args.add(OMCreator.createOMV("a"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(6), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDegreeWrtIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("deg('1+x^3','x')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}
}
