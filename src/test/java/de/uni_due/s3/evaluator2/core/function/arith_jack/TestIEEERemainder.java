package de.uni_due.s3.evaluator2.core.function.arith_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.integer1.Remainder;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
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
	public void testIEEERemainderInteger() throws OpenMathException, InputMismatchException, EvaluatorException {
		func = new Remainder();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testIEEERemainderIntegration1() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder(10,5)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testIEEERemainderIntegration2() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder(3,-2)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMI(-1), result);
	}
	
	@Test
	public void testIEEERemainderLatexSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder(3,-2)", null, null);
		String latex = Evaluator.getLaTeX(omobj);
		assertEquals("3\\mathbin{\\%}-2",latex);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIEEERemainderWithLessArguments() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder('5')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIEEERemainderWithMoreArguments() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder('2','5', 1)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIEEERemainderWithWrongArguments() throws OpenMathException, InputMismatchException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("IEEEremainder('test','5')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

}
