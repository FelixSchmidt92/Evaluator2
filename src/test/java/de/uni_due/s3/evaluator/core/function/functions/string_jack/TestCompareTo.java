package de.uni_due.s3.evaluator.core.function.functions.string_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;
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

public class TestCompareTo extends TestFunction {
	private static Function func = new CompareTo();

	@Test
	public void testCompareToEqualStrings() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Test"));
		args.add(OMCreator.createOMSTR("Test"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0), result);
	}

	@Test
	public void testCompareToNotEqualStrings() throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("Testin"));
		args.add(OMCreator.createOMSTR("Tester"));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(4), result);
	}

	@Test
	public void testCompareToIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("compareTo('Hallo', 'Test')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(-12), result.getOMI());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToWithLessThanMinParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("compareTo('Test')", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testCompareToWithMoreThanMaxParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("compareTo('Test', 'Test', 'Test')", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testCompareToWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("compareTo('Test',2)", null, null);
		OMExecutor.execute(omobj);
	}
}