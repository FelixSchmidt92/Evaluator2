package de.uni_due.s3.evaluator2.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.arith1.Abs;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestAbs extends TestFunctionAbstract {
	private Function func = new Abs();

	@Test
	public void testAbsInteger() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(-3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testAbsFloat() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(-2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(2.5), result);
	}

	@Test
	public void testAbsIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("abs(-13)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(13), result);
	}

	@Test
	public void testAbsIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("abs(10)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(10), result);
	}

	@Test
	public void testAbsSageSyntax1() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMI(5));
		assertEquals("abs(5)", func.getPartialSageSyntax(args));
	}

	@Test
	public void testAbsSageSyntax2() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.0));
		assertEquals("abs(1)", func.getPartialSageSyntax(args));
	}
	
	@Test
	public void testAbsLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("abs(5)", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("\\left|5\\right|", latex);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAbsWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("abs('test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}
