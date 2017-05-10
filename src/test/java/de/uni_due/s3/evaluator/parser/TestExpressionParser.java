package de.uni_due.s3.evaluator.parser;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.uni_due.s3.evaluator.openmath.OpenMathObjectCreator;

public class TestExpressionParser {

	@Test
	public void testIntegerValue() {
		assertTrue(ExpressionParser.parse("'1'").equals(OpenMathObjectCreator.createOpenMathInteger(1)));
		assertTrue(ExpressionParser.parse("1").equals(OpenMathObjectCreator.createOpenMathInteger(1)));
		assertTrue(ExpressionParser.parse("'5'").equals(OpenMathObjectCreator.createOpenMathInteger(5)));
		assertTrue(ExpressionParser.parse("5").equals(OpenMathObjectCreator.createOpenMathInteger(5)));
	}
	
	@Test
	public void testUnaryOperator() {
		assertTrue(ExpressionParser.parse("'-1'").equals(OpenMathObjectCreator.createOpenMathInteger(-1)));
		assertTrue(ExpressionParser.parse("-1").equals(OpenMathObjectCreator.createOpenMathInteger(-1)));
		assertTrue(ExpressionParser.parse("'+1'").equals(OpenMathObjectCreator.createOpenMathInteger(1)));
		assertTrue(ExpressionParser.parse("+1").equals(OpenMathObjectCreator.createOpenMathInteger(1)));
	}
}
