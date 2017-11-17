package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixRank extends TestFunctionAbstract {

	Function func = new RandomMatrixRank();

	@Test
	public void testRandomMatrixRankWithZerosAsArgument() throws OpenMathException, EvaluatorException {
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
	public void testRandomMatrixRankWithOnesAsArgument() throws OpenMathException, EvaluatorException {
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
	public void testRandomMatrixRank() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("ZZ"));
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(15));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(10));

		func.evaluate(args);
	}

	@Test
	public void testRandomMatrixRankIntegration4Arguments() throws OpenMathException, EvaluatorException {
		OMOBJ t = ExpressionParser.parse("randomMatrixRank('QQ', 2, 3, 2)", null, null);

		OMToResultVisitor.getInstance().visit(t);
	}

	@Test
	public void testRandomMatrixRankIntegration5Arguments() throws OpenMathException, EvaluatorException {
		OMOBJ t = ExpressionParser.parse("randomMatrixRank('QQ', 2, 3, 2, 10)", null, null);

		OMToResultVisitor.getInstance().visit(t);
	}

	@Test
	public void testRandomMatrixRankIntegration6Arguments() throws OpenMathException, EvaluatorException {
		OMOBJ t = ExpressionParser.parse("randomMatrixRank('QQ', '2', '3', '2', '10')", null, null);

		OMToResultVisitor.getInstance().visit(t);
	}
	
	@Test
	public void testRandomMatrixRankIntegration7Arguments() throws OpenMathException, EvaluatorException {
		OMOBJ t = ExpressionParser.parse("randomMatrixRank(zz(), 2, 3, 2, 10)", null, null);

		OMToResultVisitor.getInstance().visit(t);
	}
}
