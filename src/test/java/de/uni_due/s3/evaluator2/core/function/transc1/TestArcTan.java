package de.uni_due.s3.evaluator2.core.function.transc1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
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

public class TestArcTan extends TestFunctionAbstract {

	private static Function func = new ArcTan();

	@Test
	public void testArcTanFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testArcTanInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		List<Object> args2 = new ArrayList<Object>();
		args2.add(OMCreator.createOMI(1));
		args2.add(OMCreator.createOMI(4));
		List<Object> args3 = new ArrayList<Object>();
		args3.add(OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args2));
		args3.add(OMSymbol.NUMS1_PI);

		assertEquals(OMCreator.createOMA(OMSymbol.ARITH1_TIMES, args3), result);
	}

	@Test
	public void testArcTanIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan(0)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMI(0), result.getOMI());
	}

	@Test
	public void testArcTanSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.0));
		assertEquals("arctan(1)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTanWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan()", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTanWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan(1,3)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testArcTanWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan('Test')", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
}
