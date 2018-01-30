package de.uni_due.s3.evaluator2.core.function.logic1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestFalse {

	Function func = new False();
	
	@Test
	public void testFalseIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_FALSE);

		OMOBJ actual = Evaluator.evaluate("False", null, null);

		assertEquals(expected, actual);
	}

	@Test
	public void testFalseBoolean() throws EvaluatorException, OpenMathException {
		assertEquals(Boolean.valueOf(false), func.getPartialBooleanSyntax(new ArrayList<>()));
	}

	@Test
	public void testFalseDouble() throws EvaluatorException, OpenMathException {
		assertEquals(Double.valueOf(0.0), func.getPartialDoubleSyntax(new ArrayList<>()), 0);
	}

	@Test
	public void testFalseInteger() throws EvaluatorException, OpenMathException {
		assertEquals(Integer.valueOf(0), func.getPartialIntegerSyntax(new ArrayList<>()));
	}

	@Test
	public void testFalseLatex() throws EvaluatorException, OpenMathException {
		assertEquals("False", func.getPartialLatexSyntax(new ArrayList<>()));
	}

	@Test
	public void testFalseSage() throws EvaluatorException, OpenMathException {
		assertEquals("False", func.getPartialSageSyntax(new ArrayList<>()));
	}

	@Test
	public void testFalseString() throws EvaluatorException, OpenMathException {
		assertEquals("False", func.getPartialStringSyntax(new ArrayList<>()));
	}

	@Test
	public void testFalseR() throws EvaluatorException, OpenMathException {
		assertEquals("FALSE", func.getPartialRSyntax(new ArrayList<>()));
	}

	
}
