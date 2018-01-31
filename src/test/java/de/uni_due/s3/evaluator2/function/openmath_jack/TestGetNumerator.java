package de.uni_due.s3.evaluator2.function.openmath_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.openmath_jack.GetNumerator;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGetNumerator extends TestFunctionAbstract {

	Function func = new GetNumerator();

	@Test
	public void testGetDenominator1() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(10));
		omel.add(OMCreator.createOMI(15));

		args.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel));

		assertEquals(OMCreator.createOMI(10), func.evaluate(args));

	}

	@Test
	public void testGetDenominator2() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMF(1.1));
		omel.add(OMCreator.createOMF(2.2));

		args.add(OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel));

		assertEquals(OMCreator.createOMF(1.1), func.evaluate(args));

	}

	@Test
	public void TestGetNumeratorCaseIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ t = ExpressionParser.parse("getNumerator(2/3)", null, null);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		assertEquals(OMCreator.createOMI(2), actual.getOMI());
	}

	@Test
	public void TestGetNumeratorCaseIntegrationWithFloat() throws EvaluatorException, OpenMathException {
		OMOBJ t = ExpressionParser.parse("getNumerator(2.2/3)", null, null);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		assertEquals(OMCreator.createOMF(2.2), actual.getOMF());
	}
}
