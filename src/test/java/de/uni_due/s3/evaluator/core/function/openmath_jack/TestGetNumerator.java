package de.uni_due.s3.evaluator.core.function.openmath_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetNumerator extends TestFunctionAbstract {

	Function func = new GetNumerator();

	@Test
	public void testGetDenominator1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(10));
		omel.add(OMCreator.createOMI(15));

		args.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel));

		assertEquals(OMCreator.createOMI(10), func.evaluate(args));

	}

	@Test
	public void testGetDenominator2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMF(1.1));
		omel.add(OMCreator.createOMF(2.2));

		args.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel));

		assertEquals(OMCreator.createOMF(1.1), func.evaluate(args));

	}

	@Test
	public void TestGetNumeratorCaseIntegration() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ t = ExpressionParser.parse("getNumerator(2/3)", null, null);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMI(2), actual.getOMI());
	}

	@Test
	public void TestGetNumeratorCaseIntegrationWithFloat() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ t = ExpressionParser.parse("getNumerator(2.2/3)", null, null);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMF(2.2), actual.getOMF());
	}
}