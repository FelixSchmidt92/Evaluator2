package de.uni_due.s3.evaluator2.function.nums1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.nums1.E;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestE extends TestFunctionAbstract {
	
	Function func = new E();
	
	@Test
	public void testEIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.NUMS1_E);
		
		OMOBJ actual = Evaluator.evaluate("conste()", null, null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ actual = Evaluator.evaluate("[var=E]*2", null, null);
		
		assertEquals(OMCreator.createOMF(5.43656365691809), actual.getOMF());
	}
	
	@Test(expected = NoRepresentationAvailableException.class)
	public void testEBoolean() throws EvaluatorException, OpenMathException {
		assertEquals(Boolean.valueOf(true), func.getPartialBooleanSyntax(new ArrayList<>()));
	}

	@Test
	public void testEDouble() throws EvaluatorException, OpenMathException {
		assertEquals(Double.valueOf(Math.E), func.getPartialDoubleSyntax(new ArrayList<>()), 0);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testEInteger() throws EvaluatorException, OpenMathException {
		func.getPartialIntegerSyntax(new ArrayList<>());
	}

	@Test
	public void testELatex() throws EvaluatorException, OpenMathException {
		assertEquals("\\mathrm{e}", func.getPartialLatexSyntax(new ArrayList<>()));
	}

	@Test
	public void testESage() throws EvaluatorException, OpenMathException {
		assertEquals("e", func.getPartialSageSyntax(new ArrayList<>()));
	}

	@Test
	public void testEString() throws EvaluatorException, OpenMathException {
		assertEquals("E", func.getPartialStringSyntax(new ArrayList<>()));
	}

	@Test
	public void testER() throws EvaluatorException, OpenMathException {
		assertEquals("exp(1)", func.getPartialRSyntax(new ArrayList<>()));
	}
}
