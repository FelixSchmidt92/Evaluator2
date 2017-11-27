package de.uni_due.s3.evaluator2.core.function.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
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

public class TestEndsWith extends TestFunctionAbstract {
	private static Function func = new EndsWith();

	@Test
	public void testEndsWithEqualStrings() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("st"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEndsWithNotEqualStrings() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("Hello"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEndsWithIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("endsWith('Hallo', 'llo')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEndsWithWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("endsWith('Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEndsWithWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("endsWith('Test', 'Test', 'Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEndsWithWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("endsWith('Test', vector(2))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
}