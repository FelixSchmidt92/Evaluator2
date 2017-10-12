package de.uni_due.s3.evaluator2.core.function.logic1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestTrue extends TestFunctionAbstract {

	Function func = new True();

	@Test
	public void testTrueIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);

		OMOBJ actual = Evaluator.evaluate("constTrue()", null, null);

		assertEquals(expected, actual);
	}

	@Test
	public void testTrueBoolean() throws EvaluatorException {
		assertEquals(new Boolean(true), func.getPartialBooleanSyntax(new ArrayList<>()));
	}

	@Test
	public void testTrueDouble() throws EvaluatorException {
		assertEquals(new Double(1.0), func.getPartialDoubleSyntax(new ArrayList<>()), 0);
	}

	@Test
	public void testTrueInteger() throws EvaluatorException {
		assertEquals(new Integer(1), func.getPartialIntegerSyntax(new ArrayList<>()));
	}

	@Test
	public void testTrueLatex() throws EvaluatorException {
		assertEquals("True", func.getPartialLatexSyntax(new ArrayList<>()));
	}

	@Test
	public void testTrueSage() throws EvaluatorException {
		assertEquals("True", func.getPartialSageSyntax(new ArrayList<>()));
	}

	@Test
	public void testTrueString() throws EvaluatorException {
		assertEquals("True", func.getPartialStringSyntax(new ArrayList<>()));
	}

	@Test
	public void testTrueR() throws EvaluatorException {
		assertEquals("TRUE", func.getPartialRSyntax(new ArrayList<>()));
	}

}
