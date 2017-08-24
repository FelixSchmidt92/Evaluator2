package de.uni_due.s3.evaluator2.core.syntaxvisitor;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
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
	public void testOMToLatexVisitorWithStandardRepresentation() throws EvaluatorException {
		OMOBJ obj = ExpressionParser.parse("charAt('abder',3)", null, null);
		String latex = Evaluator.getLaTeX(obj);
		assertEquals("\\mbox{charAt}\\left(\\texttt{\"abder\"},3\\right)",latex);
	}
		
}
