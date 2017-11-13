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
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsPolynomial extends TestFunctionAbstract {

	Function func = new IsPolynomial();

	@Test
	public void isPolynomialWithIntegration1() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('a*2+ c', 'x')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration2() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('a*2+ c', '1')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration3() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('x^2+x+1', 'x')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration4() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ y^3', 'x')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_FALSE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration5() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ y^3', 'y')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration6() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ -y^3', 'y')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void isPolynomialWithIntegration7() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ -y^3', 'abcd')", null, null);

		OMToResultVisitor.getInstance().execute(t);
	}

	@Test(expected = CasEvaluationException.class)
	public void isPolynomialWithIntegration8() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('abcd', 'a')", null, null);

		OMToResultVisitor.getInstance().execute(t);
	}
	
	@Test
	public void isPolynomialWithIntegration9() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('2*x^2 + y^-1 + 2*x*a^-1', 'x')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}
	
	@Test
	public void isPolynomialWithIntegration10() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('0', 'x')", null, null);

		OMOBJ actual = OMToResultVisitor.getInstance().execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}
}
