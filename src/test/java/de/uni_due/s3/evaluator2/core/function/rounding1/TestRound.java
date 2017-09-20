package de.uni_due.s3.evaluator2.core.function.rounding1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRound extends TestFunctionAbstract {

	private Function func = new Round();

	@Test
	public void testRoundInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testRoundFloat1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testRoundFloat2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testRoundIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("round(12.2)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(12), result.getOMI());
	}

	@Test
	public void testRoundIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("round(10.123)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(10), result.getOMI());
	}
	
	@Test
	public void testRoundIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("round(10.123,2)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMF(10.12), result.getOMF());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRoundWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("round()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRoundWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("round(1,3,3)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testRoundWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("round('test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
