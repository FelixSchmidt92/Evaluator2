package de.uni_due.s3.evaluator2.function.set1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestUnion extends TestFunctionAbstract {

	@Test
	public void testInIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("unite(list(1,2),list(1,2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(1,2,3)", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("unite(list(1,2,3),list(1,2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(1,2,3)", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("unite(list(1,2,3),list(2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(1,2,3)", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("unite(emptySet(),list(1,2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(1,2,3)", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration5() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("unite(1,list(2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(1,2,3)", null, null);
		assertEquals(expected, result);
	}
	
	@Test
	public void testInIntegration6() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("unite(5,list(2,3))", null, null);
		OMOBJ expected = Evaluator.evaluate("list(2,3,5)", null, null);
		assertEquals(expected, result);
	}
}
