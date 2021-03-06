package de.uni_due.s3.evaluator2.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.arith1.UnaryMinus;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestUnaryMinus extends TestFunctionAbstract {

	private Function func = new UnaryMinus();

	@Test
	public void testUnaryMinusInteger() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-3), result);
	}

	@Test
	public void testUnaryMinusFloat() throws EvaluatorException, OpenMathException {

		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(-2.5), result);
	}

	@Test
	public void testUnaryMinusIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("-1075", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(-1075), result);
	}

	@Test
	public void testUnaryMinusIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("-2075.32", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMF(-2075.32), result);
	}

	@Test
	public void testUnaryMinusSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		assertEquals("-1", func.getPartialSageSyntax(args));
	}
	
	@Test
	public void testUnaryMinusLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("-3", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("-3", latex);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testUnaryMinusWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("-'test'", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
	
	@Test
	public void testUnaryPlus() throws EvaluatorException, OpenMathException {
		OMOBJ expected =new OMOBJ();
		expected.setOMI(OMCreator.createOMI(1));
		OMOBJ actual = Evaluator.evaluate("++++1", null, null);	
		assertEquals(expected, actual);
	}

}
