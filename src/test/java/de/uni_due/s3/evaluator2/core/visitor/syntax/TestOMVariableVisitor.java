package de.uni_due.s3.evaluator2.core.visitor.syntax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMVariableVisitor {

	@Test
	public void testOMVariableVisitor1() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("(a-b)", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> expected = new HashSet<>();
		expected.add(OMCreator.createOMV("a"));
		expected.add(OMCreator.createOMV("b"));
		assertEquals(expected, omvSet);
	}

	@Test
	public void testOMVariableVisitor2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("a-b*2", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> expected = new HashSet<>();
		expected.add(OMCreator.createOMV("a"));
		expected.add(OMCreator.createOMV("b"));
		assertEquals(expected, omvSet);
	}

	@Test
	public void testOMVariableVisitor3() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("(a-b)*2", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> expected = new HashSet<>();
		expected.add(OMCreator.createOMV("a"));
		expected.add(OMCreator.createOMV("b"));
		assertEquals(expected, omvSet);
	}

	@Test
	public void testOMVariableVisitor4() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("3*x^(a-b)*2", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> expected = new HashSet<>();
		expected.add(OMCreator.createOMV("a"));
		expected.add(OMCreator.createOMV("b"));
		expected.add(OMCreator.createOMV("x"));
		assertEquals(expected, omvSet);
	}

	@Test
	public void testOMVariableVisitor5() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("charAt('hello','1')", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> expected = new HashSet<>();
		expected.add(OMCreator.createOMV("e"));
		assertEquals(expected, omvSet);
	}

	@Test
	public void testOMVariableVisitor6() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("vector(1,a,z)", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> expected = new HashSet<>();
		expected.add(OMCreator.createOMV("a"));
		expected.add(OMCreator.createOMV("z"));
		assertEquals(expected, omvSet);
	}

	@Test
	public void testOMVariableVisitor7() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a*vector(a,a,a)+vector(b,c,d)", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> expected = new HashSet<>();
		expected.add(OMCreator.createOMV("d"));
		expected.add(OMCreator.createOMV("b"));
		expected.add(OMCreator.createOMV("a"));
		expected.add(OMCreator.createOMV("c"));
		assertEquals(expected, omvSet);
	}

	@Test
	public void testOMVariableVisitor8() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a^2+ c^3 + b", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> res = new HashSet<>();
		res.add(OMCreator.createOMV("a"));
		res.add(OMCreator.createOMV("c"));
		res.add(OMCreator.createOMV("b"));

		assertEquals(res, omvSet);
	}

	@Test
	public void testOMVariableVisitor9() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a*sin(c)", new HashMap<>(), new HashMap<>());
		Set<OMV> omvSet = OMVariableVisitor.getInstance().visit(obj);
		Set<OMV> res = new HashSet<>();
		res.add(OMCreator.createOMV("a"));
		res.add(OMCreator.createOMV("c"));

		assertEquals(res, omvSet);
	}
	
	@Test
	public void isSingletonPattern() throws EvaluatorException {
		OMToSyntaxVisitor<?> obj1 = OMVariableVisitor.getInstance();
		OMToSyntaxVisitor<?> obj2 = OMVariableVisitor.getInstance();
		
		assertTrue(obj1 == obj2);
	}
	
}
