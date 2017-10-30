package de.uni_due.s3.evaluator2.core.function.nums1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPi extends TestFunctionAbstract {

	Function func = new Pi();

	@Test
	public void testPiIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.NUMS1_PI);

		OMOBJ actual = Evaluator.evaluate("PI", null, null);

		assertEquals(expected, actual);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testPiBoolean() throws EvaluatorException {
		assertEquals(new Boolean(true), func.getPartialBooleanSyntax(new ArrayList<>()));
	}

	@Test
	public void testPiDouble() throws EvaluatorException {
		assertEquals(Math.PI, func.getPartialDoubleSyntax(new ArrayList<>()), 0);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testPiInteger() throws EvaluatorException {
		func.getPartialIntegerSyntax(new ArrayList<>());
	}

	@Test
	public void testPiLatex() throws EvaluatorException {
		assertEquals("\\pi", func.getPartialLatexSyntax(new ArrayList<>()));
	}

	@Test
	public void testPiSage() throws EvaluatorException {
		assertEquals("pi", func.getPartialSageSyntax(new ArrayList<>()));
	}

	@Test
	public void testPiString() throws EvaluatorException {
		assertEquals("PI", func.getPartialStringSyntax(new ArrayList<>()));
	}

	@Test
	public void testPiR() throws EvaluatorException {
		assertEquals("pi", func.getPartialRSyntax(new ArrayList<>()));
	}
}
