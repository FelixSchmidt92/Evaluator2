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

public class TestToUpperCase extends TestFunctionAbstract {
	private static Function func = new ToUpperCase();

	@Test
	public void testToUpperCaseStrings1() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("elefant"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("ELEFANT"), result);
	}

	@Test
	public void testToUpperCaseStrings2() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("LaTeX"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("LATEX"), result);
	}

	@Test
	public void testToUpperCaseStrings3() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test oder NICHT"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("TEST ODER NICHT"), result);
	}

	@Test
	public void testToUpperCaseStrings4() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR(""));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR(""), result);
	}

	@Test
	public void testToUpperCaseIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toUpperCase('Hello')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMSTR("HELLO"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testToUpperCaseWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toUpperCase(vector(1))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToUpperCaseWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toUpperCase()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToUpperCaseWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("toUpperCase('Test', 'Zwei')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}