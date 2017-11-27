package de.uni_due.s3.evaluator2.core.function.poly;

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

public class TestCoefficient extends TestFunctionAbstract {

	private Function func = new Coefficient();

	@Test
	public void testCoefficientWithOneVariable1() throws EvaluatorException, OpenMathException {
		List<Object> pow = new ArrayList<Object>();
		pow.add(OMCreator.createOMV("a"));
		pow.add(OMCreator.createOMI(2));
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(5));
		plus.add(OMCreator.createOMA(OMSymbol.ARITH1_POWER, pow));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		args.add(OMCreator.createOMV("a"));
		args.add(OMCreator.createOMI(2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(1), result);
	}

	@Test
	public void testCoefficientWithOneVariable2() throws EvaluatorException, OpenMathException {
		List<Object> pow = new ArrayList<Object>();
		pow.add(OMCreator.createOMV("a"));
		pow.add(OMCreator.createOMI(2));
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(3));
		plus.add(OMCreator.createOMA(OMSymbol.ARITH1_POWER, pow));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		args.add(OMCreator.createOMV("a"));
		args.add(OMCreator.createOMI(0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testCoefficientWithTwoVariable1() throws EvaluatorException, OpenMathException {
		List<Object> pow = new ArrayList<Object>();
		pow.add(OMCreator.createOMV("a"));
		pow.add(OMCreator.createOMI(2));
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(3));
		plus.add(OMCreator.createOMA(OMSymbol.ARITH1_POWER, pow));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		args.add(OMCreator.createOMV("x"));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testCoefficientWithTwoVariable2() throws EvaluatorException, OpenMathException {
		List<Object> pow = new ArrayList<Object>();
		pow.add(OMCreator.createOMV("a"));
		pow.add(OMCreator.createOMI(2));
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(3));
		plus.add(OMCreator.createOMA(OMSymbol.ARITH1_POWER, pow));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		args.add(OMCreator.createOMV("a"));
		args.add(OMCreator.createOMI(2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(1), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCoefficientWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testCoefficientIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("factorOf('1+2*x^3','x','3')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMI(2), result.getOMI());
	}

	@Test
	public void testCoefficientSageSyntax() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(ExpressionParser.parse("1+a^2-b", null, null));
		args.add(OMCreator.createOMV("a"));
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMV("b"));
		args.add(OMCreator.createOMI(0));
		assertEquals("var('a b');f = (( (( ( (1) + ((a)^(2)) ) )) ) - ( b )); f.coefficient(a,0)", func.getPartialSageSyntax(args));
	}
}
