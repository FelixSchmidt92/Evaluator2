package de.uni_due.s3.evaluator.core.function.polynomial_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.polynomial_jack.EqualsExpr;
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

public class TestEqualsExpr extends TestFunctionAbstract{

	private Function func = new EqualsExpr();
	private List<Object> args;
	
	@Test
	public void testEqualsExprWithCorrectPoly() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMSTR("x^2 - 5*x + 6"));
		args.add(OMCreator.createOMSTR("(x-2)*(x-3)"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE,result);
	}
	
	@Test
	public void testEqualsExprWithDiffrentPoly() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMSTR("1+a^2-b^5"));
		args.add(OMCreator.createOMSTR("x^2 - 5*x + 6"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE,result);
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsExpreWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsExprWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsExprWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testEqualsExprIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equalsExpr('1+x^3','x*x*x+1')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	
	@Test
	public void testEqualsExprSageSyntax() throws FunctionInvalidNumberOfArgumentsException, FunctionInvalidArgumentTypeException, NoRepresentationAvailableException{
		ArrayList<Object> args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMSTR("x^2 - 5*x + 6"));
		args.add(OMCreator.createOMSTR("(x-2)*(x-3)"));
		assertEquals("R.<x>=RR[]; f=x^2 - 5*x + 6; g=(x-2)*(x-3); f==g;", func.getPartialSageSyntax(args));
	}
}
