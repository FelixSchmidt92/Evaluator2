package de.uni_due.s3.evaluator2.core.function.transc2;

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

public class TestArcTan2 extends TestFunctionAbstract {

	private static Function func = new ArcTan2();

	@Test
	public void testArcTan2Float() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMF(1.0));
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
	public void testArcTan2Integer() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(0));
		Object result = func.evaluate(args);

		List<Object> args2 = new ArrayList<Object>();
		args2.add(OMCreator.createOMI(1));
		args2.add(OMCreator.createOMI(2));
		List<Object> args3 = new ArrayList<Object>();
		args3.add(OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args2));
		args3.add(OMSymbol.NUMS1_PI);

		assertEquals(OMCreator.createOMA(OMSymbol.ARITH1_TIMES, args3), result);
	}

	@Test
	public void testArcTan2Integration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan2(2,1)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMF(1.10714871779409), result.getOMF());
	}

	@Test
	public void testArcTan2SageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMF(1.1));
		assertEquals("arctan2(1,1.1)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTan2WithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan2(1)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTan2WithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan2(1,3,7)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testArcTan2WithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("atan2('Test',2)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
}
