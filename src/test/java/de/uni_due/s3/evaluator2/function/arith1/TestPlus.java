package de.uni_due.s3.evaluator2.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.arith1.Plus;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPlus extends TestFunctionAbstract {

	private Function func = new Plus();

	@Test
	public void testPlusInteger() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testPlusFloat() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.8));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(8.3), result);
	}

	@Test
	public void testPlusMixed() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMF(2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(3.5), result);
	}

	@Test
	public void testPlusIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("10+5", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(15), result);
	}

	@Test
	public void testPlusIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("plus(10,17)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(27), result);
	}

	@Test
	public void testPlusSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(1));
		assertEquals("(( (1) + (1) ))", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testPlusWithWrongArguments1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("10+'test'", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testPlusWithWrongArguments2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("plus('test',17)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test
	public void testPlusThreeArguments() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(9), result);
	}
	
	@Test
	public void testPlusFourArguments() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMI(5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(14), result);
	}
	
	@Test
	public void testPlusFourArgumentsWithFirstVariable() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMV("a"));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMI(5));
		Object result = func.getPartialSymbolicSyntax(args);
		
		
		List<Object> expArgs = new ArrayList<Object>(2);
		expArgs.add(OMCreator.createOMI(12));
		expArgs.add(OMCreator.createOMV("a"));
		OMA expected = OMCreator.createOMA(OMSymbol.ARITH1_PLUS, expArgs);
		assertEquals(expected, result);
	}
	
	@Test
	public void testPlusFourArgumentsWithLastVariable() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMV("a"));
		Object result = func.getPartialSymbolicSyntax(args);
		
		
		List<Object> expArgs = new ArrayList<Object>(2);
		expArgs.add(OMCreator.createOMI(9));
		expArgs.add(OMCreator.createOMV("a"));
		OMA expected = OMCreator.createOMA(OMSymbol.ARITH1_PLUS, expArgs);
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testPlusFourArgumentsLatex() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		args.add(OMCreator.createOMI(5));
		String result = func.getPartialLatexSyntax(args);
		assertEquals("2 + 3 + 4 + 5", result);
	}
}
