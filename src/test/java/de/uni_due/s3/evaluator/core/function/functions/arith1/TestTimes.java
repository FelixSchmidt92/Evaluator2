package de.uni_due.s3.evaluator.core.function.functions.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Times;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestTimes extends TestFunction {

	private static Times func = new Times();

	@Test
	public void testTimesInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(12), result);
	}

	@Test
	public void testTimesFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.8));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(14.5), result);
	}

	@Test
	public void testTimesMixed() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1000));
		args.add(OMCreator.createOMF(4.892));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(4892), result);
	}

	@Test
	public void testTimesIntegration1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("10*5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(50), result.getOMI());
	}

	@Test
	public void testTimesIntegration2() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("times(10,-17)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(-170), result.getOMI());
	}

	@Test
	public void testTimesSageSyntax1() throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, CasException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionInvalidArgumentTypeException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(10));
		assertEquals("1 * 10", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testTimesWithWrongArguments1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("10*'test'", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testTimesWithWrongArguments2() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("times('test',17)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

}
