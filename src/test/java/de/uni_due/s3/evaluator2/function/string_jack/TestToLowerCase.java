package de.uni_due.s3.evaluator2.function.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.string_jack.ToLowerCase;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestToLowerCase extends TestFunctionAbstract {
	private static Function func = new ToLowerCase();

	@Test
	public void testToLowerCaseStrings1() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("ELEFANT"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("elefant"), result);
	}

	@Test
	public void testToLowerCaseStrings2() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("LaTeX"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("latex"), result);
	}

	@Test
	public void testToLowerCaseStrings3() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test oder NICHT"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("test oder nicht"), result);
	}

	@Test
	public void testToLowerCaseStrings4() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR(""));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR(""), result);
	}

	@Test
	public void testToLowerCaseIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toLowerCase('Hello')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMSTR("hello"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testToLowerCaseWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toLowerCase(vector(1))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToLowerCaseWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toLowerCase()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToLowerCaseWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toLowerCase('Test', 'Zwei')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}