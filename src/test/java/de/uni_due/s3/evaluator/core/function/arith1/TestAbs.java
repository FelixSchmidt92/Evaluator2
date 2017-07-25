package de.uni_due.s3.evaluator.core.function.arith1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.arith1.Abs;
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
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestAbs extends TestFunctionAbstract {
	private Function func = new Abs();

	@Test
	public void testAbsInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(-3));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testAbsFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(-2.5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(2.5), result);
	}

	@Test
	public void testAbsIntegration1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("abs(-13)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(13), result.getOMI());
	}

	@Test
	public void testAbsIntegration2() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("abs(10)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(10), result.getOMI());
	}

	@Test
	public void testAbsSageSyntax1() throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, CasException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionInvalidArgumentTypeException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMI(5));
		assertEquals("abs(5)", func.getPartialSageSyntax(args));
	}

	@Test
	public void testAbsSageSyntax2() throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, CasException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionInvalidArgumentTypeException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.0));
		assertEquals("abs(1)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testAbsWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("abs('test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

}
