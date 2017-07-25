package de.uni_due.s3.evaluator.core.function.set_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.set_jack.GetFromOrderedSet;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
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

public class TestGetFromOrderedSet extends TestFunctionAbstract {

	private static Function func = new GetFromOrderedSet();

	@Test
	public void testGetFromOrderedSet1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMI(1));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.123));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(1));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMF(3.123), result);
	}

	@Test
	public void testGetFromOrderedSet2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMSTR("Hallo"));
		set.add(OMCreator.createOMSTR("Hello"));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("Hallo"), result);
	}

	@Test
	public void testGetFromOrderedSet3() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMF(3.343));
		set.add(OMCreator.createOMI(20));
		set.add(OMCreator.createOMF(3.243));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(2));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(20), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithLessThanMinParam()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithMoreThanMaxParam() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedSetWithWrongArguments1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1)); // wrong
		args.add(OMCreator.createOMI(1));
		func.evaluate(args);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedSetWithWrongArguments2()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> set = new ArrayList<Object>();
		set.add(OMCreator.createOMSTR("Test"));
		set.add(OMCreator.createOMI(20));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set));
		args.add(OMCreator.createOMA(OMSymbol.SET1_SET, set)); // wrong
		func.evaluate(args);
		fail();
	}

	@Test
	public void testGetFromOrderedSetIntegration1() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({1;2},0)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedSetIntegrationWrongArgsInSet() throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({1;'Test'},1)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedSetIntegrationWithOutOfBounds() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({1},2)", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testGetFromOrderedSetIntegration() throws NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionException,
			CasNotAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({matrix(matrixrow(1,2)); vector(1,2)},1)",
				null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testGetFromOrderedSetWithZeroArgs() throws NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionException,
			CasNotAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({}, 0)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithLessArgs() throws NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionException,
			CasNotAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({})", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGetFromOrderedSetWithMoreArgs() throws NoRepresentationAvailableException, CasException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionException,
			CasNotAvailableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("getFromOrderedSet({}, 0, 4)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
