package de.uni_due.s3.evaluator2.core.visitor.primitve;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.visitor.primitve.OMToIntegerVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToIntegerVisitor {

	@Test
	public void TestOMIntegerVisitor1() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1==1", new HashMap<>(), new HashMap<>());
		Integer result = OMToIntegerVisitor.getInstance().visit(obj);
		Integer expected = 1;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMIntegerVisitor2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1!=1", new HashMap<>(), new HashMap<>());
		Integer result = OMToIntegerVisitor.getInstance().visit(obj);
		Integer expected = 0;
		assertEquals(expected, result);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void TestOMIntegerVisitor3() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1.3", new HashMap<>(), new HashMap<>());
		OMToIntegerVisitor.getInstance().visit(obj);
	}

	@Test
	public void TestOMIntegerVisitor4() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("0.0", new HashMap<>(), new HashMap<>());
		Integer result = OMToIntegerVisitor.getInstance().visit(obj);
		Integer expected = 0;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMIntegerVisitor5() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1", new HashMap<>(), new HashMap<>());
		Integer result = OMToIntegerVisitor.getInstance().visit(obj);
		Integer expected = 1;
		assertEquals(expected, result);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void TestOMIntegerVisitor6() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("PI", new HashMap<>(), new HashMap<>());
		OMToIntegerVisitor.getInstance().visit(obj);
	}

	@Test
	public void TestOMIntegerVisitor7() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'True'", new HashMap<>(), new HashMap<>());
		assertEquals(OMSymbol.LOGIC1_TRUE, obj.getOMS());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void TestOMIntegerVisitor8() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a", new HashMap<>(), new HashMap<>());
		OMToIntegerVisitor.getInstance().visit(obj);
	}
}
