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

public class TestTrim extends TestFunctionAbstract {
	private static Function func = new Trim();

	@Test
	public void testTrimStrings1() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test    "));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Test"), result);
	}

	@Test
	public void testTrimStrings2() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("    Hello World! "));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Hello World!"), result);
	}

	@Test
	public void testTrimIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("trim('  ja ')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMSTR("ja"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testTrimWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("trim()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testTrimWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("trim('Test', 'Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testTrimWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("trim(vector(3))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}