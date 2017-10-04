package de.uni_due.s3.evaluator2.core.function.nums1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

//TODO implement mor Tests
public class TestE extends TestFunctionAbstract {
	
	Function func = new E();
	
	@Test
	public void testEIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.NUMS1_E);
		
		OMOBJ actual = Evaluator.evaluate("constE()", null, null);
		
		assertEquals(expected, actual);
	}
	
	
}
