package de.uni_due.s3.evaluator2.core.function.nums1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.nums1.NaN;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestNaN {

	Function func = new NaN();
	
	//we do not want the user to create NaN-Objects!
	@Test(expected = FunctionNotImplementedException.class)
	public void testNaNIntegration() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("constNaN()", null, null);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testNaNBoolean() throws EvaluatorException, OpenMathException {
		func.getPartialBooleanSyntax(new ArrayList<>());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testNaNDouble() throws EvaluatorException, OpenMathException {
		func.getPartialDoubleSyntax(new ArrayList<>());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testNaNInteger() throws EvaluatorException, OpenMathException {
		func.getPartialIntegerSyntax(new ArrayList<>());
	}

	@Test
	public void testNaNLatex() throws EvaluatorException, OpenMathException {
		assertEquals("NaN", func.getPartialLatexSyntax(new ArrayList<>()));
	}

	@Test
	public void testNaNSage() throws EvaluatorException, OpenMathException {
		assertEquals("NaN", func.getPartialSageSyntax(new ArrayList<>()));
	}

	@Test
	public void testNaNString() throws EvaluatorException, OpenMathException {
		assertEquals("NaN", func.getPartialStringSyntax(new ArrayList<>()));
	}

	@Test
	public void testNaNR() throws EvaluatorException, OpenMathException {
		assertEquals("NaN", func.getPartialRSyntax(new ArrayList<>()));
	}
}
