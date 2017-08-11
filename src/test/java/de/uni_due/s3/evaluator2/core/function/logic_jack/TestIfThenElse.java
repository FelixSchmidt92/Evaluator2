package de.uni_due.s3.evaluator2.core.function.logic_jack;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.function.logic_jack.IfThenElse;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.sage.Sage;

public class TestIfThenElse extends TestFunctionAbstract {

	private final Function func = new IfThenElse();

	private List<Object> args;
	private Object result;

	@Test
	public void testIfThenElseConditionOMF() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(3.0));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("true"), result);

		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMF(-1.0));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("false"), result);
	}

	@Test
	public void testIfThenElseConditionOMI() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("true"), result);

		args = new ArrayList<Object>(3);
		args.add(OMCreator.createOMI(-2));
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("false"), result);
	}

	@Test
	public void testIfThenElseConditionBoolean() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("true"), result);

		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_FALSE);
		args.add(OMCreator.createOMSTR("true"));
		args.add(OMCreator.createOMSTR("false"));
		result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("false"), result);
	}

	@Test
	public void testIfThenElseAnyReturnType() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		Object obj = new Object();
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(obj);
		args.add(null);
		result = func.evaluate(args);
		assertEquals(obj, result);

	}

	@Test
	public void testIfThenEleseIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("ifthenelse('5*3*9>=10','true','false')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("true", result.getOMSTR().getContent());

		omobj = ExpressionParser.parse("ifthenelse('5*3*10==10','true','false')", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("false", result.getOMSTR().getContent());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIfThenEleseWithLessThanMinParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testIfThenEleseWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIfThenEleseWithWrongArguments() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		args = new ArrayList<Object>(2);
		args.add(null);
		args.add(OMCreator.createOMSTR(null));
		args.add(OMCreator.createOMSTR("test"));
		func.evaluate(args);
		fail();
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testIfThenElseSageSyntax() throws FunctionException, NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException,
			CasNotAvailableException, OpenMathException {
		args = new ArrayList<Object>(3);
		args.add(OMSymbol.LOGIC1_TRUE);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMSTR("false"));
		Sage.evaluateInCAS(func.getPartialSageSyntax(args));
		fail();
	}

}
