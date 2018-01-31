package de.uni_due.s3.evaluator2.function.logic1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.logic1.And;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestAnd extends TestFunctionAbstract {

	private final Function func = new And();

	@Test
	public void TestBooleanAndWithBothArgumentsTrue() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_TRUE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void TestBooleanAndWithOneTrueOneFalse1() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_FALSE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void TestBooleanAndWithOneTrueOneFalse2() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_TRUE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void TestBooleanAndWithBothArgumentsFalse() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}
	
	@Test
	public void testBooleanAndLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("3 && 2", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("3\\mbox{and}2", latex);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanAndWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanAndWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithWrongArguments() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.NUMS1_RATIONAL);
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
	}

	@Test
	public void testBooleanAndIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("countNodes(1+2)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMI(4), result.getOMI());
	}

	@Test
	public void testBooleanAndSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_TRUE);
		assertEquals("True & True", func.getPartialSageSyntax(args));
	}

	@Test
	public void testBooleaAndPartialEvaluation() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("FALSE && evaluateInSage('Something not executable')", new HashMap<>(), new HashMap<>());
		OMOBJ actual = Evaluator.evaluate(obj);
		OMOBJ expected = OMCreator.createOMOBJ(OMSymbol.LOGIC1_FALSE);
		assertEquals(expected, actual);
	}

}
