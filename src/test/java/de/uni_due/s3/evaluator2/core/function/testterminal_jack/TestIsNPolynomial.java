package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsNPolynomial extends TestFunctionAbstract {

	Function func = new IsNPolynomial();

	@Test
	public void isPolynomialWithIntegration1() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('a*2+ c', x, 1)", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_FALSE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration2() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('a*2+ c',a,'1')", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration3() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('x^2+x+1', 'x',2)", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration4() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('sin(x)+ y^3', 'x',1)", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_FALSE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration5() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('sin(x)+ y^3', 'y',3)", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration6() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('sin(x)+ -y^3', 'y',0)", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_FALSE);
		assertEquals(expected, actual);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void isPolynomialWithIntegration7() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('sin(x)+ -y^3', 'abcd',1)", null, null);

		OMToResultVisitor.getInstance().visit(t);
	}

	@Test(expected = CasEvaluationException.class)
	public void isPolynomialWithIntegration8() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('abcd', 'a',1)", null, null);

		OMToResultVisitor.getInstance().visit(t);
	}
	
	@Test
	public void isPolynomialWithIntegration9() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('2*x^2 + y^-1 + 2*x*a^-1', 'x',2)", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}
	
	@Test
	public void isPolynomialWithIntegration10() throws EvaluatorException, OpenMathException {

		OMOBJ t = ExpressionParser.parse("isNPolynomial('0', 'x',0)", null, null);

		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(t));

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}
}
