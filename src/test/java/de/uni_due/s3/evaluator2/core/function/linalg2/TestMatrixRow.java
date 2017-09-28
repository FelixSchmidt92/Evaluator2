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
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMatrixRow extends TestFunctionAbstract {

	private static ConstructorFunction func = new MatrixRow();

	@Test
	public void testMatrixRowInteger() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args), result);
	}

	@Test
	public void testMatrixRowFloat() throws OpenMathException, EvaluatorException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args), result);
	}

	@Test
	public void testMatrixRowIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("matrixrow(1,3,0.0)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMF(0.0));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args), result.getOMA());
	}

	@Test
	public void testMatrixRowSageSyntax() throws EvaluatorException {
		List<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(0.0));
		args.add(OMCreator.createOMI(1));
		assertEquals("[0,1]", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testMatrixRowWithLessThanMinParam() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("matrixrow()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}
	
	@Test
	public void testMatrixRowLatexSyntax() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("matrixrow(1,2,3)", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("1 & 2 & 3", latex);
	}
}
