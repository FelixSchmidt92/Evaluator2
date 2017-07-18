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

public class TestGreaterThanOrEqual extends TestFunction {

	private final Function func = new GreaterThanOrEqual();

	@Test
	public void testGreaterThanOrEqualOMI1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testGreaterThanOrEqualOMI2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(109345));
		args.add(OMCreator.createOMI(109345));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testGreaterThanOrEqualOMF1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMF(0.546362));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testGreaterThanOrEqualOMF2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(12.3045));
		args.add(OMCreator.createOMF(12.3045));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testGreaterThanOrEqualOMFAndOMI1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(0.546363));
		args.add(OMCreator.createOMI(-3));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testGreaterThanOrEqualOMFAndOMI2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(20));
		args.add(OMCreator.createOMF(20.00f));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGreaterThanOrEqualWithLessThanMinParam()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGreaterThanOrEqualWithMoreThanMaxParam()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGreaterThanOrEqualWithWrongArguments()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(null);
		args.add(OMCreator.createOMSTR(null));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testGreaterThanOrEqualIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("10 >= 5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testGreaterThanSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(9));
		assertEquals("10 >= 9", func.getPartialSageSyntax(args));
	}

}
