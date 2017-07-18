package de.uni_due.s3.evaluator.core.function.functions.set1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
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

public class TestSize extends TestFunction {

	private Function func = new Size();
	private List<Object> args;
	private Object result;

	@Test
	public void testSize() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(1);
		List<Object> arguments = new ArrayList<Object>();
		arguments.add(OMCreator.createOMI(10));
		arguments.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, arguments));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testSizeWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(0);
		func.evaluate(args);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testSizeWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testSizeWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(null);
		result = func.evaluate(args);
	}

	@Test
	public void testSizeIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("size({1;2;3})", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}
}
