package de.uni_due.s3.evaluator.core.function.logic1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.logic1.BooleanOr;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestBooleanOr extends TestFunctionAbstract {

	private final Function func = new BooleanOr();

	@Test
	public void TestBooleanOrWithBothArgumentsTrue() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_TRUE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void TestBooleanOrWithOneTrueOneFalse1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_FALSE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void TestBooleanOrWithOneTrueOneFalse2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_TRUE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void TestBooleanOrWithBothArgumentsFalse() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanOrWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testBooleanOrWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testBooleanOrWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.ARITH1_DIVIDE);
		args.add(OMSymbol.LOGIC1_FALSE);
		func.evaluate(args);
		fail();
	}

	@Test
	public void testBooleanOrIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("(1<2) || (2==1)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testBooleanOrSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException, FunctionInvalidArgumentTypeException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMSymbol.LOGIC1_TRUE);
		assertEquals("True | True", func.getPartialSageSyntax(args));
	}

}
