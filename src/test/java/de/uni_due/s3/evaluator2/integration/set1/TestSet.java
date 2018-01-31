package de.uni_due.s3.evaluator2.integration.set1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSet extends TestIntegration {

	@Test
	public void testSet() throws EvaluatorException, OpenMathException {
		assertEquals(ExpressionParser.parse("set(vector(1,1), vector(1,2))", exerciseVariableMap, fillInVariableMap),
				Evaluator.evaluate("set(vector(1,1), vector(1,2))", exerciseVariableMap, fillInVariableMap));
	}
}
