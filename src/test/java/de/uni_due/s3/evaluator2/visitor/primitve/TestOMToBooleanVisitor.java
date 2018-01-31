package de.uni_due.s3.evaluator2.visitor.primitve;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToBooleanVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToBooleanVisitor {

	@Test
	public void TestOMBooleanVisitor1() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1==1", new HashMap<>(), new HashMap<>());
		Boolean result = OMToBooleanVisitor.getInstance().visit(obj);
		Boolean expected = true;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor2() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1!=1", new HashMap<>(), new HashMap<>());
		Boolean result = OMToBooleanVisitor.getInstance().visit(obj);
		Boolean expected = false;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor3() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1", new HashMap<>(), new HashMap<>());
		Boolean result = OMToBooleanVisitor.getInstance().visit(obj);
		Boolean expected = true;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor4() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("0", new HashMap<>(), new HashMap<>());
		Boolean result = OMToBooleanVisitor.getInstance().visit(obj);
		Boolean expected = false;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor5() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("1.0", new HashMap<>(), new HashMap<>());
		Boolean result = OMToBooleanVisitor.getInstance().visit(obj);
		Boolean expected = true;
		assertEquals(expected, result);
	}

	@Test
	public void TestOMBooleanVisitor6() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("0.0", new HashMap<>(), new HashMap<>());
		Boolean result = OMToBooleanVisitor.getInstance().visit(obj);
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
		OMToBooleanVisitor.getInstance().visit(obj);
	}
	
	@Test
	public void isSingletonPattern() throws EvaluatorException, OpenMathException {
		OMToSyntaxVisitor<?> obj1 = OMToBooleanVisitor.getInstance();
		OMToSyntaxVisitor<?> obj2 = OMToBooleanVisitor.getInstance();
		
		assertTrue(obj1 == obj2);
	}
}
