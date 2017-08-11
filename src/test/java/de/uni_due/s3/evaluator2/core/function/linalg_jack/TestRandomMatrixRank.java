package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.function.linalg_jack.RandomMatrixRank;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixRank extends TestFunctionAbstract {

	Function func = new RandomMatrixRank();

	@Test
	public void testRandomMatrixRankWithZerosAsArgument()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("QQ"));
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMI(0));
		args.add(OMCreator.createOMI(0));

		Object actual = func.evaluate(args);
		OMA expected = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, new ArrayList<>());

		assertEquals(expected, actual);
	}

	@Test
	public void testRandomMatrixRankWithOnesAsArgument()
			throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("QQ"));
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(1));

		Object actual = func.evaluate(args);
		ArrayList<Object> omel = new ArrayList<>();
		ArrayList<Object> omel2 = new ArrayList<>();
		omel2.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, omel2));

		OMA expected = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, omel);

		assertEquals(expected, actual);
	}

	@Test
	public void testRandomMatrixRank() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("ZZ"));
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(15));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(10));

		func.evaluate(args);
	}

	@Test
	public void testRandomMatrixRankIntegration4Arguments() throws FunctionInvalidArgumentException,
			CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException,
			OpenMathException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ t = ExpressionParser.parse("randomMatrixRank('QQ', 2, 3, 2)", null, null);

		OMExecutor.execute(t);
	}

	@Test
	public void testRandomMatrixRankIntegration5Arguments() throws FunctionInvalidArgumentException,
			CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException,
			OpenMathException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ t = ExpressionParser.parse("randomMatrixRank('QQ', 2, 3, 2, 10)", null, null);

		OMExecutor.execute(t);
	}
	
	@Test
	public void testRandomMatrixRankIntegration6Arguments() throws FunctionInvalidArgumentException,
			CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException,
			OpenMathException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ t = ExpressionParser.parse("randomMatrixRank('QQ', '2', '3', '2', '10')", null, null);

		OMExecutor.execute(t);
	}
}
