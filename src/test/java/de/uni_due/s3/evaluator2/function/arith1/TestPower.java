package de.uni_due.s3.evaluator2.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.arith1.Power;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPower extends TestFunctionAbstract {

	private Power func = new Power();

	@Test
	public void testPowerWithInteger() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(8), result);
	}

	@Test
	public void testPowerWithFloat() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.4));
		args.add(OMCreator.createOMF(3.9));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(Math.pow(2.4, 3.9)), result);
	}

	@Test
	public void testPowerWithMixedArgs() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMF(3.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(Math.pow(2, 3.5)), result);
	}

	@Test
	public void testPowerIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("pow(2,3)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(8), result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testPowerWithInvalidArgumentType() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("pow(5,'Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test
	public void testPowerSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.45));
		args.add(OMCreator.createOMF(3));
		assertEquals("((1.45)^(3))", func.getPartialSageSyntax(args));
	}
}
