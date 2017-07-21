package de.uni_due.s3.evaluator.core.function.functions.polynomial_jack;

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

public class TestEvalTerm2 extends TestFunction{

	private Function func = new EvalTerm2();
	private List<Object> args;
	
	@Test
	public void testEvalEqWithCorrectPoly() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMSTR("x^2 - 5*y + 6"));
		args.add(OMCreator.createOMSTR("1"));
		args.add(OMCreator.createOMSTR("2"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-3),result);
	}
	
	@Test
	public void testEvalEqWithDiffrentPoly() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMSTR("1+a^2-b^5"));
		args.add(OMCreator.createOMSTR("1"));
		args.add(OMCreator.createOMSTR("2"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(-30),result);
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalEqWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEvalEqWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEvalEqWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMV("test"));
		args.add(OMCreator.createOMV("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEvalEqIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("evalterm2('x+y','1','2')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}

	
	@Test
	public void testEvalEqSageSyntax() throws FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, NoRepresentationAvailableException{
		ArrayList<Object> args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMSTR("x^2 - 5*y + 6"));
		args.add(OMCreator.createOMSTR("1"));
		args.add(OMCreator.createOMSTR("3"));
		assertEquals("R.<x,y>=RR[]; f=x^2 - 5*y + 6; f(1,3);", func.getPartialSageSyntax(args));
	}
}
