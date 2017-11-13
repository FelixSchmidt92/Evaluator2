package de.uni_due.s3.evaluator2.core.visitor.primitve;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.visitor.primitve.OMToStringVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToStringVisitor {

	@Test
	public void TestOMStringVisitor1() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1==1", new HashMap<>(), new HashMap<>());
		String result = OMToStringVisitor.getInstance().visit(obj);
		String expected = "True";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMStringVisitor2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1!=1", new HashMap<>(), new HashMap<>());
		String result = OMToStringVisitor.getInstance().visit(obj);
		String expected = "False";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMStringVisitor3() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1", new HashMap<>(), new HashMap<>());
		String result = OMToStringVisitor.getInstance().visit(obj);
		String expected = "1";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMStringVisitor4() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("0", new HashMap<>(), new HashMap<>());
		String result = OMToStringVisitor.getInstance().visit(obj);
		String expected = "0";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMStringVisitor5() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1.1", new HashMap<>(), new HashMap<>());
		String result = OMToStringVisitor.getInstance().visit(obj);
		String expected = "1.1";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMStringVisitor6() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'Test  '", new HashMap<>(), new HashMap<>());
		String result = OMToStringVisitor.getInstance().visit(obj);
		String expected = "Test  ";
		assertEquals(expected, result);
	}

	@Test
	public void TestOMStringVisitor7() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a", new HashMap<>(), new HashMap<>());
		String result = OMToStringVisitor.getInstance().visit(obj);
		String expected = "a";
		assertEquals(expected, result);
	}
}
