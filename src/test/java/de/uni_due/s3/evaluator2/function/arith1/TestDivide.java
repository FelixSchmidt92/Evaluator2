package de.uni_due.s3.evaluator2.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.arith1.Divide;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDivide extends TestFunctionAbstract {

	private static Function func = new Divide();

	@Test
	public void testDivideInteger() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(0.75), result);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testDivideZero() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(0));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDivideFloat() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.6));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(2.5 / 5.6), result);
	}

	@Test
	public void testDivideMixed() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1000));
		args.add(OMCreator.createOMF(2.00));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(500), result);
	}

	@Test
	public void testDivideIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("10/5", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testDivideIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("divide(1,-4)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMF(-0.25), result);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testDivideIntegrationWithWrongParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("10/0", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testDivideIntegrationWithWrongParam2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("10/(1-1.0)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test
	public void testDivideSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(10));
		assertEquals("(( 1 ) / ( 10 ))", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDivideWithWrongArguments1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("10/'test'", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDivideWithWrongArguments2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("a/'test'", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
	}
	
	@Test
	public void testDivideLatex() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.2));
		args.add(OMCreator.createOMI(5));
		
		String result = func.getPartialLatexSyntax(args);
		
		assertEquals("\\frac{1.2}{5}", result);
	}
	

}
