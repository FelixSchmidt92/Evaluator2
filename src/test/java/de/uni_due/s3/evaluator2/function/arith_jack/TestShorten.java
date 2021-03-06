package de.uni_due.s3.evaluator2.function.arith_jack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestShorten extends TestFunctionAbstract {

	@Test
	public void testShortenIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("shorten(10.1)", null, null);
		assertEquals(OMCreator.createOMIOMF(10.1), result.getOMF());
	}
	
	@Test
	public void testShortenIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("shorten('string')", null, null);
		assertEquals(OMCreator.createOMSTR("string"), result.getOMSTR());
	}
	
	@Test
	public void testShortenIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("shorten(rational(1,2))", null, null);
		assertEquals("<OMOBJ><OMA><OMS name=\"rational\" cd=\"nums1\"/><OMI>1</OMI><OMI>2</OMI></OMA></OMOBJ>", result.toString());
	}
	
	@Test
	public void testShortenIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("shorten(rational(2,2))", null, null);
		assertEquals(OMCreator.createOMI(1), result.getOMI());
	}
	
	@Test
	public void testShortenIntegration5() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("shorten(rational(4*x,2))", null, null);
		assertEquals("<OMOBJ><OMA><OMS name=\"times\" cd=\"arith1\"/><OMI>2</OMI><OMV name=\"x\"/></OMA></OMOBJ>", result.toString());
	}
	
	@Test
	public void testShortenIntegration6() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("shorten(rational(4*x*a*z^3,2*z))", null, null);
		assertEquals("<OMOBJ>"
				+ "<OMA>"
					+ "<OMS name=\"times\" cd=\"arith1\"/>"
					+ "<OMI>2</OMI>"
						
					+ "<OMA>"
						+ "<OMS name=\"power\" cd=\"arith1\"/>"
						+ "<OMV name=\"z\"/>"
						+ "<OMI>2</OMI>"
					+ "</OMA>"
					
					+ "<OMV name=\"x\"/>"
								
					+ "<OMV name=\"a\"/>"
				
				+ "</OMA>"
				+ "</OMOBJ>",
				result.toString());
	}
	
}
