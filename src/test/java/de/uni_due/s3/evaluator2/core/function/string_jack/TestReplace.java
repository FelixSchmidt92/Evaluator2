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

public class TestReplace extends TestFunctionAbstract {
	private static Function func = new Replace();

	@Test
	public void testReplaceStrings1() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("elefant"));
		args.add(OMCreator.createOMSTR("f"));
		args.add(OMCreator.createOMSTR("g"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("elegant"), result);
	}

	@Test
	public void testReplaceStrings2() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Hello"));
		args.add(OMCreator.createOMSTR("H"));
		args.add(OMCreator.createOMSTR("Te"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Teello"), result);
	}

	@Test
	public void testReplaceStrings3() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Funktion"));
		args.add(OMCreator.createOMSTR("Fun"));
		args.add(OMCreator.createOMSTR("San"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Sanktion"), result);
	}

	@Test
	public void testReplaceStrings4() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("elefant"));
		args.add(OMCreator.createOMSTR("z"));
		args.add(OMCreator.createOMSTR("y"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("elefant"), result);
	}

	@Test
	public void testReplaceCaseIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("replace('Hello', 'e', 'a')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMCreator.createOMSTR("Hallo"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testReplaceWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("replace('Hello', '1', vector(3))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testReplaceWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("replace('Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testReplaceWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("replace('Test', 'Zwei', 'Zwei', 'Zwei')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}