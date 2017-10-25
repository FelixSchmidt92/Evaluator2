package de.uni_due.s3.evaluator2.core.visitor.syntax;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToLatexVisitor {
	
	@Test
	public void testOMToLatexVisitorWithoutParenthesisAndOneOperation() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("(a-b)", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("a-b", latex);
	}
	@Test
	public void testOMToLatexVisitorWithoutParenthesisAndTwoOperation() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("a-b*2", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("a-b \\cdot 2", latex);
	}
	@Test
	public void testOMToLatexVisitorWithParenthesis() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("(a-b)*2", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("\\left(a-b\\right) \\cdot 2", latex);
	}
	
	
	@Test
	public void testOMToLatexVisitorWithPolynomial() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("3*x^(a-b)*2", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("3 \\cdot {x}^{\\left(a-b\\right)} \\cdot 2", latex);
	}
	
	@Test
	public void testOMToLatexVisitorWithStandardRepresentation() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("charAt('abder',3)", null, null);
		String latex = Evaluator.getLaTeX(obj);
		assertEquals("\\mbox{charAt}\\left(abder,3\\right)",latex);
	}
	
	@Test
	public void testOMToLatexvisitorWithNormalString() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'hello'", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("hello", latex);
	}
	
	@Test
	public void testOMToLatexvisitorWithEmptyString() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'  '", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("  ", latex);
	}
	
	@Test
	public void testOMToLatexvisitorWithLatexCommandInString() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'\\sum{hello}'", new HashMap<>(), new HashMap<>());
		String latex = new OMToLatexVisitor().visit(obj);
		assertEquals("\\sum{hello}", latex);
	}
		
}
