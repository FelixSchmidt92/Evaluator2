package de.uni_due.s3.evaluator2.function.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.string_jack.Equals;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEquals extends TestFunctionAbstract {
	private static Function func = new Equals();

	@Test
	public void testEqualsEqualStrings() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("Test"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsNotEqualStrings() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("est"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEqualsIntegrationTrue() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equals('Hello', 'Hello')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualsIntegrationFalse() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equals('Hallo', 'llo')", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equals('Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithMoreThanMaxParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equals('Test', 'Test', 'Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("equals('Test', vector(2))", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
	
	
	@Test
	public void testEqualsIntegrationwithI() throws EvaluatorException, OpenMathException {
		OMOBJ res = Evaluator.evaluate("equals('+I', '+I')", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, res.getOMS());
	}
	
	@Test
	public void testEqualsIntegrationwithI2() throws EvaluatorException, OpenMathException {
		OMOBJ res = Evaluator.evaluate("equals('I', '+I')", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, res.getOMS());
	}
	
	@Test
	public void testEqualsIntegrationwithI3() throws EvaluatorException, OpenMathException {
		OMOBJ res = Evaluator.evaluate("equals('+II', '+II')", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, res.getOMS());
	}
	
}