package de.uni_due.s3.evaluator.core.function.functions.transc2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
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

public class TestArcTan2 extends TestFunction {

	private static Function func = new ArcTan2();

	@Test
	public void testArcTan2Float() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMF(1.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(0.785398163397448), result);
	}

	@Test
	public void testArcTan2Integer() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(1.57079632679490), result);
	}

	@Test
	public void testArcTan2Integration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("atan2(2,1)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMF(1.10714871779409), result.getOMF());
	}

	@Test
	public void testArcTan2SageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException, FunctionInvalidArgumentTypeException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMF(1.1));
		assertEquals("arctan2(1,1.1)", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTan2WithLessThanMinParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("atan2(1)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testArcTan2WithMoreThanMaxParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("atan2(1,3,7)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testArcTan2WithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("atan2('Test',2)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
