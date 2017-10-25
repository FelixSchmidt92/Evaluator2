package de.uni_due.s3.evaluator2.core.visitor.primitve;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.visitor.primitve.OMToDoubleVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToDoubleVisitor {

	@Test
	public void TestOMDoubleVisitor1() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1==1", new HashMap<>(), new HashMap<>());
		Double result = new OMToDoubleVisitor().visit(obj);
		Double expected = 1.0;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMDoubleVisitor2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1!=1", new HashMap<>(), new HashMap<>());
		Double result = new OMToDoubleVisitor().visit(obj);
		Double expected = 0.0;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMDoubleVisitor3() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1.3", new HashMap<>(), new HashMap<>());
		Double result = new OMToDoubleVisitor().visit(obj);
		Double expected = 1.3;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMDoubleVisitor4() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("0.123", new HashMap<>(), new HashMap<>());
		Double result = new OMToDoubleVisitor().visit(obj);
		Double expected = 0.123;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMDoubleVisitor5() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1", new HashMap<>(), new HashMap<>());
		Double result = new OMToDoubleVisitor().visit(obj);
		Double expected = 1.0;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMDoubleVisitor6() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("0", new HashMap<>(), new HashMap<>());
		Double result = new OMToDoubleVisitor().visit(obj);
		Double expected = 0.0;
		assertEquals(expected, result);
	}
	
	@Test
	public void TestOMDoubleVisitor7() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("PI", new HashMap<>(), new HashMap<>());
		Double result = new OMToDoubleVisitor().visit(obj);
		Double expected = Math.PI;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMDoubleVisitor8() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'True'", new HashMap<>(), new HashMap<>());
		assertEquals(OMSymbol.LOGIC1_TRUE, obj.getOMS());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void TestOMDoubleVisitor9() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a", new HashMap<>(), new HashMap<>());
		new OMToDoubleVisitor().visit(obj);
	}
}
