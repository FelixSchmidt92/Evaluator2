package de.uni_due.s3.evaluator.core.function.functions.linalg2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
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
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMatrix extends TestFunction {

	private OMI omi1 = OMCreator.createOMI(1);
	private OMI omi2 = OMCreator.createOMI(2);
	private OMI omi3 = OMCreator.createOMI(3);
	private OMI omi4 = OMCreator.createOMI(4);
	private OMF omf = OMCreator.createOMF(0.0);
	private static Function func = OMSFunctionDictionary.getInstance()
			.getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("matrix"));

	@Test
	public void testMatrixInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(omi1);
		args.add(omi2);
		args.add(omi3);
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		Object result = func.evaluate(matrix);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result);
	}

	@Test
	public void testMatrixFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException,
			NoRepresentationAvailableException, OpenMathException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(omf);
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
		matrixrow1.add(omi1);
		matrixrow1.add(omi3);
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow1));
		List<Object> matrixrow2 = new ArrayList<Object>();
		matrixrow2.add(omf);
		matrixrow2.add(omi4);
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow2));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result.getOMA());
	}

	@Test
	public void testMatrixSageSyntax()
			throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException {
		List<Object> matrix = new ArrayList<>();
		List<Object> matrixrow1 = new ArrayList<>();
		matrixrow1.add(omf);
		matrixrow1.add(omi2);
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow1));
		List<Object> matrixrow2 = new ArrayList<>();
		matrixrow2.add(omi1);
		matrixrow2.add(omi3);
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, matrixrow2));
		assertEquals("matrix([[0.0,2],[1,3]])", func.getPartialSageSyntax(matrix));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMatrixWithLessThanMinParam() throws FunctionException, OpenMathException, CasEvaluationException,
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
	}
}
