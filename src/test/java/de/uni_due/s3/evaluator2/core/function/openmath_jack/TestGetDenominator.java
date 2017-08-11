package de.uni_due.s3.evaluator2.core.function.openmath_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.function.openmath_jack.GetDenominator;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetDenominator extends TestFunctionAbstract {

	Function func = new GetDenominator();

	@Test
	public void testGetDenominator1() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(10));
		omel.add(OMCreator.createOMI(15));

		args.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel));

		assertEquals(OMCreator.createOMI(15), func.evaluate(args));

	}

	@Test
	public void testGetDenominator2() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMF(1.1));
		omel.add(OMCreator.createOMF(2.2));

		args.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel));

		assertEquals(OMCreator.createOMF(2.2), func.evaluate(args));

	}

	@Test
	public void TestGetDenominatorCaseIntegration() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ t = ExpressionParser.parse("getDenominator(2/3)", null, null);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMI(3), actual.getOMI());
	}

	@Test
	public void TestGetDenominatorCaseIntegrationWithFloat() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ t = ExpressionParser.parse("getDenominator(2/3.3)", null, null);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMF(3.3), actual.getOMF());
	}

}
