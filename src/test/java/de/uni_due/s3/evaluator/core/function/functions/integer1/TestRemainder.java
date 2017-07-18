package de.uni_due.s3.evaluator.core.function.functions.integer1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
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

public class TestRemainder extends TestFunction {

	private static Remainder func = new Remainder();

	@Test
	public void testModulusInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		Remainder func = new Remainder();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(5));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(3), result);
	}

	@Test
	public void testModulusIntegration1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("10%5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(0), result.getOMI());
	}

	@Test
	public void testModulusIntegration2() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("remainder(3,-2)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test
	public void testModulusSageSyntax() throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, CasException, FunctionNotImplementedException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(10));
		assertEquals("1 % 10", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMinusWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("10%'test'", null, null);
		OMExecutor.execute(omobj);
	}

}
