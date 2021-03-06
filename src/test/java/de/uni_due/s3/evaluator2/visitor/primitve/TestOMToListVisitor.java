package de.uni_due.s3.evaluator2.visitor.primitve;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToListVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToListVisitor {
	
	@Test
	public void TestOMListVisitor1() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1==1", new HashMap<>(), new HashMap<>());
		List<Object> result = OMToListVisitor.getInstance().visit(obj);
		List<Object> expected = new ArrayList<>();
		expected.add(OMSymbol.LOGIC1_TRUE);
		assertEquals(expected, result);
	}

	@Test
	public void TestOMListVisitor2() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1!=1", new HashMap<>(), new HashMap<>());
		List<Object> result = OMToListVisitor.getInstance().visit(obj);
		List<Object> expected = new ArrayList<>();
		expected.add(OMSymbol.LOGIC1_FALSE);
		assertEquals(expected, result);
	}

	@Test
	public void TestOMListVisitor3() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1", new HashMap<>(), new HashMap<>());
		List<Object> result = OMToListVisitor.getInstance().visit(obj);
		List<Object> expected = new ArrayList<>();
		expected.add(OMCreator.createOMI(1));
		assertEquals(expected, result);
	}

	@Test
	public void TestOMListVisitor4() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("0", new HashMap<>(), new HashMap<>());
		List<Object> result = OMToListVisitor.getInstance().visit(obj);
		List<Object> expected = new ArrayList<>();
		expected.add(OMCreator.createOMI(0));
		assertEquals(expected, result);
	}

	@Test
	public void TestOMListVisitor5() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("[var=PI]", new HashMap<>(), new HashMap<>());
		List<Object> result = OMToListVisitor.getInstance().visit(obj);
		List<Object> expected = new ArrayList<>();
		expected.add(OMSymbol.NUMS1_PI);
		assertEquals(expected, result);
	}

	@Test
	public void TestOMListVisitor6() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'Test  '", new HashMap<>(), new HashMap<>());
		List<Object> result = OMToListVisitor.getInstance().visit(obj);
		List<Object> expected = new ArrayList<>();
		expected.add(OMCreator.createOMSTR("Test  "));
		assertEquals(expected, result);
	}

	@Test
	public void TestOMListVisitor7() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a", new HashMap<>(), new HashMap<>());
		List<Object> result = OMToListVisitor.getInstance().visit(obj);
		List<Object> expected = new ArrayList<>();
		expected.add(OMCreator.createOMV("a"));
		assertEquals(expected, result);
	}
	
	@Test
	public void isSingletonPattern() throws EvaluatorException, OpenMathException {
		OMToSyntaxVisitor<?> obj1 = OMToListVisitor.getInstance();
		OMToSyntaxVisitor<?> obj2 = OMToListVisitor.getInstance();
		
		assertTrue(obj1 == obj2);
	}
}
