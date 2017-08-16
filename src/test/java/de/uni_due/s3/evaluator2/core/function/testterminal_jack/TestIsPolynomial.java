package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsPolynomial extends TestFunctionAbstract {

	Function func = new IsPolynomial();

	@Test
	public void isPolynomialWithIntegration1() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('a*2+ c', 'x')", null, null);

		OMOBJ actual = OMExecutor.execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration2() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('a*2+ c', '1')", null, null);

		OMOBJ actual = OMExecutor.execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration3() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('x^2+x+1', 'x')", null, null);

		OMOBJ actual = OMExecutor.execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration4() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ y^3', 'x')", null, null);

		OMOBJ actual = OMExecutor.execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_FALSE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration5() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ y^3', 'y')", null, null);

		OMOBJ actual = OMExecutor.execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test
	public void isPolynomialWithIntegration6() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ -y^3', 'y')", null, null);

		OMOBJ actual = OMExecutor.execute(t);

		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, actual);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void isPolynomialWithIntegration7() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('sin(x)+ -y^3', 'abcd')", null, null);

		OMExecutor.execute(t);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void isPolynomialWithIntegration8() throws OpenMathException, EvaluatorException {

		OMOBJ t = ExpressionParser.parse("isPolynomial('abcd', 'a')", null, null);

		OMExecutor.execute(t);
	}
}