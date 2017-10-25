package de.uni_due.s3.evaluator2.core.function.rounding1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestFloor extends TestFunctionAbstract {

	private Function func = new Floor();

	@Test
	public void testFloorInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testFloorFloat1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testFloorFloat2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.8));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testFloorIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("floor(12.2)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(12), result.getOMI());
	}

	@Test
	public void testFloorIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("floor(10)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(10), result.getOMI());
	}
	
	@Test
	public void testFloorLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("floor(10)", null, null);
		String latex = Evaluator.getLaTeX(omobj);
		assertEquals("\\left\\lfloor 10 \\right\\rfloor",latex);
	}
	

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testFloorWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("floor()", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testFloorWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("floor(1,3)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testFloorWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("floor('test')", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
}
