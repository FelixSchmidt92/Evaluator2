package de.uni_due.s3.evaluator2.core.function.linalg2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMatrix extends TestFunctionAbstract {

	private static Function func = new Matrix();

	@Test
	public void testMatrixInteger() throws OpenMathException, EvaluatorException {
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
	public void testMatrixFloat() throws OpenMathException, EvaluatorException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		Object result = func.evaluate(matrix);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix), result);
	}

	@Test
	public void testMatrixIntegration() throws OpenMathException, EvaluatorException {
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
	public void testMatrixSageSyntax() throws EvaluatorException {
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
	public void testMatrixWithZeroParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("matrix()", null, null);
		OMExecutor.execute(omobj);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testMatrixWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("matrix('Test')", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test
	public void testMatrixLatexSyntax() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("matrix(matrixrow(1,2,3),matrixrow(4,5))", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("\\left(\\begin{array}{rr}"
				+ "1 & 2 & 3\\\\"
				+ "4 & 5\\\\"
				+ "\\end{array}\\right)" 
				, latex);
	}
}
