package de.uni_due.s3.evaluator2.core.function.arith_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.integer1.Remainder;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.openmath.InputMismatchException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIEEERemainder {
	private static Function func = new IEEERemainder();

	@Test
	public void testModulusInteger() throws OpenMathException, InputMismatchException, EvaluatorException {
		func = new Remainder();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testModulusIntegration1() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder(10,5)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(0), result.getOMI());
	}

	@Test
	public void testModulusIntegration2() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder(3,-2)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(-1), result.getOMI());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMinusWithLessArguments() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder('5')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMinusWithMoreArguments() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder('2','5', 1)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMinusWithWrongArguments() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder('test','5')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

}
