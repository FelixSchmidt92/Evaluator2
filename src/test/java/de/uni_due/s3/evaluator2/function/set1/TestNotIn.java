package de.uni_due.s3.evaluator2.function.set1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestNotIn extends TestFunctionAbstract {

	@Test
	public void testInIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isNotElementOf(1,list(1,2,3))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testInIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isNotElementOf(vector(1,3,2),list(1,2,3,vector(1,3,2)))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testInIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isNotElementOf(vector(1,3,2),list(1,2,3,vector(1,3,3)))", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isNotElementOf(2,2)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
	
	@Test
	public void testInIntegration5() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isNotElementOf(2,0)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, result.getOMS());
	}
	
	@Test
	public void testInIntegration6() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("isNotElementOf(emptySet(),0)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, result.getOMS());
	}
}
