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

public class TestI extends TestFunctionAbstract {
	
	Function func = new I();
	
	@Test
	public void testIIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.NUMS1_I);
		
		OMOBJ actual = Evaluator.evaluate("imaginary()", null, null);
		
		assertEquals(expected, actual);
	}
	
	@Test(expected = NoRepresentationAvailableException.class)
	public void testIBoolean() throws EvaluatorException, OpenMathException {
		func.getPartialBooleanSyntax(new ArrayList<>());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testIDouble() throws EvaluatorException, OpenMathException {
		func.getPartialDoubleSyntax(new ArrayList<>());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testIInteger() throws EvaluatorException, OpenMathException {
		func.getPartialIntegerSyntax(new ArrayList<>());
	}

	@Test
	public void testILatex() throws EvaluatorException, OpenMathException {
		assertEquals("\\mathrm{i}", func.getPartialLatexSyntax(new ArrayList<>()));
	}

	@Test
	public void testISage() throws EvaluatorException, OpenMathException {
		assertEquals("I", func.getPartialSageSyntax(new ArrayList<>()));
	}

	@Test
	public void testIString() throws EvaluatorException, OpenMathException {
		assertEquals("I", func.getPartialStringSyntax(new ArrayList<>()));
	}

	@Test
	public void testIR() throws EvaluatorException, OpenMathException {
		assertEquals("complex(0,0,1)", func.getPartialRSyntax(new ArrayList<>()));
	}

}
