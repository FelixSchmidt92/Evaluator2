package de.uni_due.s3.evaluator2.core.visitor.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMCountVisitor {
	
	@Test
	public void testOMCountVisitor1() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("(a-b)", new HashMap<>(), new HashMap<>());
		Integer result = OMCountVisitor.getInstance().visit(obj);
		Integer expected = 4; //OMA OMS OMV OMV
		assertEquals(expected, result);
	}
	@Test
	public void testOMCountVisitor2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("a-b*2", new HashMap<>(), new HashMap<>());
		Integer result = OMCountVisitor.getInstance().visit(obj);
		Integer expected = 7;
		assertEquals(expected, result);
	}
	@Test
	public void testOMCountVisitor3() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("(a-b)*2", new HashMap<>(), new HashMap<>());
		Integer result = OMCountVisitor.getInstance().visit(obj);
		Integer expected = 7;
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testOMCountVisitor4() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("3*x^(a-b)*2", new HashMap<>(), new HashMap<>());
		Integer result = OMCountVisitor.getInstance().visit(obj);
		Integer expected = 13;
		assertEquals(expected, result);
	}
	
	@Test
	public void testOMCountVisitor5() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("charAt('hello','1')", new HashMap<>(), new HashMap<>());
		Integer result = OMCountVisitor.getInstance().visit(obj);
		Integer expected = 1;
		assertEquals(expected, result);
	}
	
	@Test
	public void testOMCountVisitor6() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("vector(1,a,z)", new HashMap<>(), new HashMap<>());
		Integer result = OMCountVisitor.getInstance().visit(obj);
		Integer expected = 5;
		assertEquals(expected, result);
	}
	
	@Test
	public void testOMCountVisitor7() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a*vector(a,a,a)+vector(b,c,d)", new HashMap<>(), new HashMap<>());
		Integer result = OMCountVisitor.getInstance().visit(obj);
		Integer expected = 15;
		assertEquals(expected, result);
	}		
	
	@Test
	public void isSingletonPattern() throws EvaluatorException {
		OMToSyntaxVisitor<?> obj1 = OMCountVisitor.getInstance();
		OMToSyntaxVisitor<?> obj2 = OMCountVisitor.getInstance();
		
		assertTrue(obj1 == obj2);
	}
}