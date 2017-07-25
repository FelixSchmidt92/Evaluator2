package de.uni_due.s3.evaluator.core.function.linalg2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.linalg2.Matrix;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
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

public class TestMatrix extends TestFunctionAbstract {

	private static Function func = new Matrix();

	@Test
	public void testMatrixInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		Object result = func.evaluate(matrix);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result);
	}

	@Test
	public void testMatrixFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		Object result = func.evaluate(matrix);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result);
	}

	@Test
	public void testMatrixIntegration() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("matrix(matrixrow(1,3),matrixrow(0.0,4))", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> matrix = new ArrayList<Object>();
		List<Object> matrixrow1 = new ArrayList<Object>();
		matrixrow1.add(OMCreator.createOMI(1));
		matrixrow1.add(OMCreator.createOMI(3));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow1));
		List<Object> matrixrow2 = new ArrayList<Object>();
		matrixrow2.add(OMCreator.createOMF(0.0));
		matrixrow2.add(OMCreator.createOMI(4));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow2));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result.getOMA());
	}

	@Test
	public void testMatrixSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException, FunctionInvalidArgumentTypeException {
		List<Object> matrix = new ArrayList<>();
		List<Object> matrixrow1 = new ArrayList<>();
		matrixrow1.add(OMCreator.createOMF(0.0));
		matrixrow1.add(OMCreator.createOMI(2));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow1));
		List<Object> matrixrow2 = new ArrayList<>();
		matrixrow2.add(OMCreator.createOMI(1));
		matrixrow2.add(OMCreator.createOMI(3));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow2));
		assertEquals("matrix([[0,2],[1,3]])", func.getPartialSageSyntax(matrix));
	}

	@Test
	public void testMatrixWithZeroParam() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("matrix()", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMatrixWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("matrix('Test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
}
