package de.uni_due.s3.evaluator2.core.function.cas_jack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvaluateInSage extends TestFunctionAbstract{

	@Test
	public void testEvaluateInSageIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("evaluateInSage('var(\\' a b c d \\'); matrix(SR,2,2,[a,b,c,d])')", null, null);
		assertEquals("<OMOBJ><OMA><OMS name=\"matrix\" cd=\"linalg2\"/><OMA><OMS name=\"matrixrow\" cd=\"linalg2\"/><OMV name=\"a\"/><OMV name=\"b\"/></OMA><OMA><OMS name=\"matrixrow\" cd=\"linalg2\"/><OMV name=\"c\"/><OMV name=\"d\"/></OMA></OMA></OMOBJ>", result.toString());
	}
}
