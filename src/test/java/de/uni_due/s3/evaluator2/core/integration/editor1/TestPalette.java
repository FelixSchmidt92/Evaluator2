package de.uni_due.s3.evaluator2.core.integration.editor1;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Tests only that the tested methods are supported by the evaluator
 * @author frichtscheid
 *
 */
public class TestPalette {

	@Test
	public void testPaletteArith1() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("palette("
				+ "paletterow("
					+ "plus(),minus(),times(),divide(),abs(),"
					+ "sum(),root(),product()))", null, null);
		
	}
	
	
	
	
}
