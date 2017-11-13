package de.uni_due.s3.evaluator2.core.function.set1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSubset extends TestFunctionAbstract {

	@Test
	public void testInIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(1,list(1,2,3))", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(vector(1,3,2),list(1,2,3,vector(1,3,2)))", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(vector(1,3,2),list(1,2,3,vector(1,3,3)))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testInIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(2,2)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration5() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(2,0)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testInIntegration6() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(emptySet(),list(1,2))", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration7() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(list(3,4),list(1,2))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testInIntegration8() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(list(1,2),list(1,2))", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration9() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isSubsetOf(list(1,3),list(1,2))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
}
