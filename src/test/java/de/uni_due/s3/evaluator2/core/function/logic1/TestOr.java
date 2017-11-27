package de.uni_due.s3.evaluator2.core.function.logic1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.core.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOr extends TestFunctionAbstract {

	private final Function func = new Or();

	@Test
	public void TestBooleanOrWithBothArgumentsTrue() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_TRUE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void TestBooleanOrWithOneTrueOneFalse1() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_FALSE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void TestBooleanOrWithOneTrueOneFalse2() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_TRUE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void TestBooleanOrWithBothArgumentsFalse() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}
	
	@Test
	public void testBooleanOrLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("3 || 2", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("3\\mbox{or}2", latex);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanOrWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanOrWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanOrWithWrongArguments() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.ARITH1_DIVIDE);
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
	}

	@Test
	public void testBooleanOrIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("(1<2) || (2==1)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testBooleanOrSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_TRUE);
		assertEquals("True | True", func.getPartialSageSyntax(args));
	}
	
	@Test
	public void testBooleaOrPartialEvaluation() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("TRUE || evaluateInSage('Something not executable')", new HashMap<>(), new HashMap<>());
		OMOBJ actual = Evaluator.evaluate(obj);
		OMOBJ expected = OMCreator.createOMOBJ(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

}
