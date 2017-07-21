package de.uni_due.s3.evaluator.core.function.functions.relation1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
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
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestNotEqual extends TestFunction {

	private final Function func = new NotEqual();

	@Test
	public void testNotEqualsOMI1() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(10));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMI2() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(120976));
		args.add(OMCreator.createOMI(12));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMF1() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMF(0.546363));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMF2() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(12.3045));
		args.add(OMCreator.createOMF(12.3044));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMS1() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.ARITH1_PLUS);
		args.add(OMSymbol.ARITH1_PLUS);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMS2() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMSymbol.RELATION1_EQ);
		args.add(OMSymbol.ARITH1_PLUS);
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMA1() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> arguments = new ArrayList<Object>(3);
		arguments.add(OMCreator.createOMI(10));
		arguments.add(OMCreator.createOMI(-10));
		arguments.add(OMCreator.createOMI(30));
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments));
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMA2() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> arguments = new ArrayList<Object>(3);
		ArrayList<Object> arguments2 = new ArrayList<Object>(3);

		arguments.add(OMCreator.createOMI(10));
		arguments.add(OMCreator.createOMI(-10));
		arguments.add(OMCreator.createOMI(30));
		arguments2.add(OMCreator.createOMI(10));
		arguments2.add(OMCreator.createOMI(20));
		arguments2.add(OMCreator.createOMI(30));

		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments));
		args.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, arguments2));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testNotEqualsOMSTR1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testNotEqualsOMSTR2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("tes t"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testNotEqualsWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testNotEqualsWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(null);
		args.add(OMCreator.createOMSTR(null));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testNotEqualsIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("notequal(10,3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testNotEqualsSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException, FunctionInvalidArgumentTypeException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(1));
		assertEquals("10 != 1", func.getPartialSageSyntax(args));
	}

}
