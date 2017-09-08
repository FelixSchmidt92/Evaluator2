package de.uni_due.s3.evaluator2.core.function.linalg1;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestDeterminant extends TestFunctionAbstract{

	private final Function func = new Determinant();
	
	@Test
	public void testDeterminantLatexSyntax() throws FunctionException, NoRepresentationAvailableException, EvaluatorException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		
		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix);
		args = new ArrayList<Object>();
		args.add(oma);
		assertEquals("\\det{"
				+ "\\left(\\begin{array}{ll}"
						+ "\\begin{array}{rr}1 & 2\\end{array}\\\\"
						+ "\\begin{array}{rr}1 & 2\\end{array}\\\\"
						+ "\\end{array}\\right)"
				+ "}",func.getPartialLatexSyntax(args));
	}
	
	@Test
	public void testDeterminantSageSyntax() throws FunctionException, NoRepresentationAvailableException, EvaluatorException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		
		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix);
		args = new ArrayList<Object>();
		args.add(oma);
		assertEquals("M=matrix([[1,2],[1,2]]); M.determinant()",func.getPartialSageSyntax(args));
	}
	
	@Test
	public void testDeterminant() throws FunctionException, NoRepresentationAvailableException, EvaluatorException, OpenMathException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		
		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix);
		args = new ArrayList<Object>();
		args.add(oma);
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0),result);
	}
	
	@Test(expected = CasEvaluationException.class)
	public void testDeterminantWithWrongMatrixSize() throws FunctionException, NoRepresentationAvailableException, EvaluatorException, OpenMathException {
		List<Object> matrix = new ArrayList<Object>();
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		//matrix.add(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args));
		
		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrix);
		args = new ArrayList<Object>();
		args.add(oma);
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMI(0),result);
		fail();
	}
	
	@Test
	public void testDeterminantIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("det(matrix(matrixrow(1,2),matrixrow(1,2)))", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMI(0), result.getOMI());
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testDeterminantWithZeroParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("det()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testDeterminantWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("det(3)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
}
