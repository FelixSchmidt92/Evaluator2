package de.uni_due.s3.evaluator2.core.function.transc_jack;

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

public class TestToDegree extends TestFunctionAbstract {

	private static Function func = new ToDegree();

	@Test
	public void testToDegreePI() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(Math.PI));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(180), result);
	}

	@Test
	public void testToDegreeMinusPI() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(-Math.PI));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-180), result);
	}

	@Test
	public void testToDegreeFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testToDegreeIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("toDegree(1)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMF(57.29577951308232), result.getOMF());
	}

	@Test
	public void testToDegreeIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("toDegree(7)", null, null);
		OMOBJ result = new OMToResultVisitor().execute(omobj);
		assertEquals(OMCreator.createOMF(401.07045659157626), result.getOMF());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToDegreeWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("toDegree()", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToDegreeWithMoreThanMaxParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("toDegree(1,3)", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testToDegreeWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("toDegree('Test')", null, null);
		new OMToResultVisitor().execute(omobj);
		fail();
	}
}
