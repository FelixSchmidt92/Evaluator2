package de.uni_due.s3.evaluator2.core.function.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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

public class TestCharAt extends TestFunctionAbstract {
	private static Function func = new CharAt();

	@Test
	public void testCharAtInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("e"), result);
	}

	@Test
	public void testCharAtFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMF(1.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("e"), result);
	}

	@Test
	public void testCharAtIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("charAt('Hallo', 3)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMSTR("l"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCharAtWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("charAt('Test')", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCharAtWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("charAt('Test',2,'Test')", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testCharAtWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("charAt('Test','Te')", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
}