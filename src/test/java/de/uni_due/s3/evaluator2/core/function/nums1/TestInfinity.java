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

public class TestInfinity extends TestFunctionAbstract {

	Function func = new Infinity();
	
	@Test
	public void testInfinityIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.NUMS1_INFINITY);
		
		OMOBJ actual = Evaluator.evaluate("INFINITY", null, null);
		
		assertEquals(expected, actual);
	}
	
	@Test(expected = NoRepresentationAvailableException.class)
	public void testInfinityBoolean() throws EvaluatorException {
		assertEquals(new Boolean(true), func.getPartialBooleanSyntax(new ArrayList<>()));
	}

	@Test
	public void testInfinityDouble() throws EvaluatorException {
		assertEquals(Double.POSITIVE_INFINITY, func.getPartialDoubleSyntax(new ArrayList<>()), 0);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testInfinityInteger() throws EvaluatorException {
		func.getPartialIntegerSyntax(new ArrayList<>());
	}

	@Test
	public void testInfinityLatex() throws EvaluatorException {
		assertEquals("\\\\infty", func.getPartialLatexSyntax(new ArrayList<>()));
	}

	@Test
	public void testInfinitySage() throws EvaluatorException {
		assertEquals("Infinity", func.getPartialSageSyntax(new ArrayList<>()));
	}

	@Test
	public void testInfinityString() throws EvaluatorException {
		assertEquals("Infinity", func.getPartialStringSyntax(new ArrayList<>()));
	}

	@Test
	public void testInfinityR() throws EvaluatorException {
		assertEquals("Inf", func.getPartialRSyntax(new ArrayList<>()));
	}
	
}
