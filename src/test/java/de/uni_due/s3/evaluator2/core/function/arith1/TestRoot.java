package de.uni_due.s3.evaluator2.core.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.core.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRoot extends TestFunctionAbstract {
	private Function func = new Root();

	@Test
	public void testRootInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(9));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testRootFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(6.25));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(2.5), result);
	}

	@Test
	public void testRootIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(25)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(5), result);
	}

	@Test
	public void testRootIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(25)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(5), result);
	}
	

	@Test
	public void testRootLatexSyntaxOneArgument() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("root(3)", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("\\sqrt{3}", latex);
	}
	
	@Test
	public void testRootLatexSyntaxTwoArguments() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("root(10,3)", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("\\sqrt[3]{10}", latex);
	}

	@Test
	public void testRootIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(125, 3)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(5), result);
	}

	@Test
	public void testRootIntegration4() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root(-125, 3)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(-5), result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAbsWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("root('test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}
