package de.uni_due.s3.evaluator2.core.function.arith_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
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

public class TestMax extends TestFunctionAbstract {

	private Function func = new Max();

	@Test
	public void testMaxInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testMaxFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.8));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(5.8), result);
	}

	@Test
	public void testMaxMixed() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMF(2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(2.5), result);
	}

	@Test
	public void testMaxIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("max(5,10)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(10), result.getOMI());
	}

	@Test
	public void testMaxIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("max(10.1,17)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(17), result.getOMI());
	}
	
	@Test
	public void testMaxLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("max(10,2)", null,null);
		String latex = Evaluator.getLaTeX(omobj);
		assertEquals("\\mbox{max}\\left(10,2\\right)",latex);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMaxWithWrongArguments1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("max(10,'test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMaxWithWrongArguments2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("max('test',17)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMaxWithWrongNumberOfArgs1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("max('test',17, 3)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMaxWithWrongNumberOfArgs2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("max('test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
