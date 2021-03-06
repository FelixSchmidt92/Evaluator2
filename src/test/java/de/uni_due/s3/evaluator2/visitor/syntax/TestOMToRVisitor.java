package de.uni_due.s3.evaluator2.visitor.syntax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToRVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToRVisitor {

	
	@Test
	public void TestOMRVisitor1() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1==1", new HashMap<>(), new HashMap<>());
		String result = OMToRVisitor.getInstance().visit(obj);
		String expected = "TRUE";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMRVisitor2() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1!=1", new HashMap<>(), new HashMap<>());
		String result = OMToRVisitor.getInstance().visit(obj);
		String expected = "FALSE";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMRVisitor3() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1", new HashMap<>(), new HashMap<>());
		String result = OMToRVisitor.getInstance().visit(obj);
		String expected = "1";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMRVisitor4() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("0", new HashMap<>(), new HashMap<>());
		String result = OMToRVisitor.getInstance().visit(obj);
		String expected = "0";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMRVisitor5() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("constpi()", new HashMap<>(), new HashMap<>());
		String result = OMToRVisitor.getInstance().visit(obj);
		String expected = "pi";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMRVisitor6() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'Test  '", new HashMap<>(), new HashMap<>());
		String result = OMToRVisitor.getInstance().visit(obj);
		String expected = "Test  ";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMRVisitor7() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a", new HashMap<>(), new HashMap<>());
		String result = OMToRVisitor.getInstance().visit(obj);
		String expected = "a";
		assertEquals(expected, result);
	}
	
	@Test
	public void isSingletonPattern() throws EvaluatorException, OpenMathException {
		OMToSyntaxVisitor<?> obj1 = OMToRVisitor.getInstance();
		OMToSyntaxVisitor<?> obj2 = OMToRVisitor.getInstance();
		
		assertTrue(obj1 == obj2);
	}
}
