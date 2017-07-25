package de.uni_due.s3.evaluator.core.function.string_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.string_jack.EqualsIgnoreCase;
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

public class TestEqualsIgnoreCase extends TestFunctionAbstract {
	private static Function func = new EqualsIgnoreCase();

	@Test
	public void testEqualsIgnoreCaseEqualStrings() throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("Test"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, result);
	}

	@Test
	public void testEqualsIgnoreCaseNotEqualStrings() throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("est"));
		Object result = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, result);
	}

	@Test
	public void testEqualsIgnoreCaseIntegrationTrue() throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equalsIgnoreCase('Hello', 'Hello')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}

	@Test
	public void testEqualsIgnoreCaseIntegrationFalse() throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equalsIgnoreCase('Hallo', 'llo')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsIgnoreCaseWithLessThanMinParam() throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equalsIgnoreCase('Test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testEqualsIgnoreCaseWithMoreThanMaxParam() throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equalsIgnoreCase('Test', 'Test', 'Test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsIgnoreCaseWithWrongArguments() throws FunctionException, OpenMathException,
			CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("equalsIgnoreCase('Test',2)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}