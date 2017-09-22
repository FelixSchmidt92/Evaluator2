package de.uni_due.s3.evaluator2.core.function.cas_jack;

import org.junit.Test;
import static org.junit.Assert.*;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvaluateInR extends TestFunctionAbstract{
	
	@Test
	public void testEvaluateInRInteger() throws OpenMathException, EvaluatorException {
	
		OMOBJ result = Evaluator.evaluate("evaluateInR('9')",null,null);
		assertEquals(OMCreator.createOMI(9),result.getOMI());
	}
	
	@Test
	public void testEvaluateInRFloat() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('9.123')", null, null);
		OMOBJ result = Evaluator.evaluate(omobj);
		assertEquals(OMCreator.createOMF(9.123),result.getOMF());
	}
	
	@Test
	public void testEvaluateInRString() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('c(1)')", null, null);
		OMOBJ result = Evaluator.evaluate(omobj);
		assertEquals(OMCreator.createOMI(1),result.getOMI());
	}
	
	@Test
	public void testEvaluateInRBool() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('e<-TRUE;e')", null, null);
		OMOBJ result = Evaluator.evaluate(omobj);
		assertEquals(OMSymbol.LOGIC1_TRUE,result.getOMS());
	}
	
	@Test
	public void testEvaluateInRList() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('e<-list(1,2,3);e[1]')", null, null);
		OMOBJ result = Evaluator.evaluate(omobj);
		assertEquals(OMSymbol.LIST1_LIST, result.getOMA().getOmel().get(0));
		assertEquals(OMCreator.createOMI(1), result.getOMA().getOmel().get(1));
	}
	
}
