package de.uni_due.s3.evaluator2.core.function.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestLastIndexOf extends TestFunctionAbstract {
	private static Function func = new LastIndexOf();

	@Test
	public void testLastIndexOfTwoParams() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Tester"));
		args.add(OMCreator.createOMSTR("e"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(4), result);
	}

	@Test
	public void testLastIndexOfThreeParams() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Tester"));
		args.add(OMCreator.createOMSTR("e"));
		args.add(OMCreator.createOMI(3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(1), result);
	}

	@Test
	public void testLastIndexOfIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("lastIndexOf('Hallo', 'l', 3)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}
	
	@Test
	public void testLastIndexOfIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("lastIndexOf('Hello World!', 'o')", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(7), result.getOMI());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testLastIndexOfWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("lastIndexOf('Test')", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testLastIndexOfWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("lastIndexOf('Test', 2 ,'Test', 3)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testLastIndexOfWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("lastIndexOf('Test', vector(4))", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
}