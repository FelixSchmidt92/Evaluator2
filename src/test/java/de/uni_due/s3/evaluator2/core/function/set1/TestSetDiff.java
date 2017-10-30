package de.uni_due.s3.evaluator2.core.function.set1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSetDiff extends TestFunctionAbstract {

	@Test
	public void testInIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("setDiff(list(1,2),list(1,2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list()", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("setDiff(list(1,2,3),list(1,2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list()", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("setDiff(list(1,2,3),list(2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(1)", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("setDiff(emptySet(),list(1,2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list()", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration5() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("setDiff(1,list(2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(1)", null, null);
		assertEquals(expected, result);
	}
}
