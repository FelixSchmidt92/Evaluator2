package de.uni_due.s3.evaluator2.core.function.poly;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.function.poly.Degree_wrt;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDegree_wrt extends TestFunctionAbstract {

	private Function func = new Degree_wrt();

	@Test
	public void testDegreeWrtWithOneVariable() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> pow = new ArrayList<Object>();
		pow.add(OMCreator.createOMV("a"));
		pow.add(OMCreator.createOMI(2));
		List<Object> plus = new ArrayList<Object>();
		plus.add(OMCreator.createOMI(1));
		plus.add(OMCreator.createOMA(OMSymbol.ARITH1_POWER, pow));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus));
		args.add(OMCreator.createOMV("a"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(2), result);
	}

	@Test
	public void testDegreeWrtWithTwoVariable() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(ExpressionParser.parse("1+a^2-b^5", null, null));
		args.add(OMCreator.createOMV("b"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(5), result);
	}

	@Test
	public void testDegreeWrtWithEmptyVariable() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(ExpressionParser.parse("1+a^6-b^5", null, null));
		args.add(OMCreator.createOMV("a"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(6), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDegreeWrtWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test
	public void testDegreeWrtIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("deg('1+x^3','x')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(3), result.getOMI());
	}

	@Test
	public void testDegreeWrtSageSyntax() throws FunctionException, NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		ArrayList<Object> args = new ArrayList<Object>();
		args.add(ExpressionParser.parse("1+a^2-b", null, null));
		args.add(OMCreator.createOMV("a"));
		assertEquals("var('a b');f = ((1 + (a)^(2)) - b); f.degree(a)", func.getPartialSageSyntax(args));
	}
}
