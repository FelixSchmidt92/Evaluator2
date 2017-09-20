package de.uni_due.s3.evaluator2.core.function.cas_jack;

import org.junit.Ignore;
import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

@Ignore
public class TestEvaluateInR extends TestFunctionAbstract{
	
	@Test
	public void testEvaluateInRInteger() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('9')", null, null);
		OMExecutor.execute(omobj);
	}
	
	@Test
	public void testEvaluateInRFloat() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('9.123')", null, null);
		OMExecutor.execute(omobj);
	}
	
	@Test
	public void testEvaluateInRString() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('c(1)')", null, null);
		OMExecutor.execute(omobj);
	}
	
	@Test
	public void testEvaluateInRBool() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('TRUE')", null, null);
		OMExecutor.execute(omobj);
	}
	
	@Test
	public void testEvaluateInRList() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('list(1,2,3)')", null, null);
		OMExecutor.execute(omobj);
	}
	
}
