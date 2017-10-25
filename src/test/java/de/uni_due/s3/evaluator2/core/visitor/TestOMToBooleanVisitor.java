package de.uni_due.s3.evaluator2.core.visitor;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToBooleanVisitor {

	@Test
	public void TestOMBooleanVisitor1() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1==1", new HashMap<>(), new HashMap<>());
		Boolean result = new OMToBooleanVisitor().visit(obj);
		Boolean expected = true;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1!=1", new HashMap<>(), new HashMap<>());
		Boolean result = new OMToBooleanVisitor().visit(obj);
		Boolean expected = false;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor3() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1", new HashMap<>(), new HashMap<>());
		Boolean result = new OMToBooleanVisitor().visit(obj);
		Boolean expected = true;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor4() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("0", new HashMap<>(), new HashMap<>());
		Boolean result = new OMToBooleanVisitor().visit(obj);
		Boolean expected = false;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor5() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("1.0", new HashMap<>(), new HashMap<>());
		Boolean result = new OMToBooleanVisitor().visit(obj);
		Boolean expected = true;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor6() throws OpenMathException, EvaluatorException {
		OMOBJ obj = Evaluator.evaluate("0.0", new HashMap<>(), new HashMap<>());
		Boolean result = new OMToBooleanVisitor().visit(obj);
		Boolean expected = false;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor7() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("'True'", new HashMap<>(), new HashMap<>());
		assertEquals(OMSymbol.LOGIC1_TRUE, obj.getOMS());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void TestOMBooleanVisitor8() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("a", new HashMap<>(), new HashMap<>());
		new OMToBooleanVisitor().visit(obj);
	}
}
