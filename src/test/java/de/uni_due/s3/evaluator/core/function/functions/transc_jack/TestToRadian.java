package de.uni_due.s3.evaluator.core.function.functions.transc_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
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

public class TestToRadian extends TestFunction {

	private static Function func = new ToRadian();

	@Test
	public void testToRadianPI() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(180));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(Math.PI), result);
	}

	@Test
	public void testToRadianMinusPI() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(-180));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(-Math.PI), result);
	}
	
	@Test
	public void testToRadianFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testToRadianIntegration1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("toRadian(180)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMF(Math.PI), result.getOMF());
	}
	
	@Test
	public void testToRadianIntegration2() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("toRadian(720)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMF(Math.PI*4), result.getOMF());
	}


	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToRadianWithLessThanMinParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("toRadian()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testToRadianWithMoreThanMaxParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("toRadian(1,3)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testToRadianWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("toRadian('Test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
