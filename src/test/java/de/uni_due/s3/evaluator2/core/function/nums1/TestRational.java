package de.uni_due.s3.evaluator2.core.function.nums1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRational extends TestFunctionAbstract {

	Function func = new Rational();
	
	@Test
	public void testRationalInteger() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		OMA expected = OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args);
		
		assertEquals(expected, result);
	}

	@Test
	public void testRationalZero() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(0));
		Object result = func.evaluate(args);
		
		OMA expected = OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args);
		
		assertEquals(expected, result);
	}

	@Test
	public void testRationalFloat() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.6));
		Object result = func.evaluate(args);
		
		OMA expected = OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args);
		
		assertEquals(expected, result);
	}

	@Test
	public void testRationalMixed() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1000));
		args.add(OMCreator.createOMF(2.00));
		Object result = func.evaluate(args);
		OMA expected = OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args);
		
		assertEquals(expected, result);
	}

	@Test
	public void testRationalIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("rational(10, 5)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(5));
		
		OMOBJ expected = new OMOBJ();
		
		expected.setOMA(OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args));
		
		assertEquals(expected, result);
	}

	@Test
	public void testRationalIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("rational(1,-4)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(-4));
		
		OMOBJ expected = new OMOBJ();
		
		expected.setOMA(OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args));
		
		assertEquals(expected, result);
	}

	@Test
	public void testRationalIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("rational(10, 0)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMI(0));
		
		OMOBJ expected = new OMOBJ();
		
		expected.setOMA(OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, args));
		
		assertEquals(expected, result);
	}

	

	@Test
	public void testRationalSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(1.0));
		args.add(OMCreator.createOMI(10));
		assertEquals("1/10", func.getPartialSageSyntax(args));
	}

	
	@Test(expected = FunctionNotImplementedException.class)
	public void testRationalPalette() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("palette(paletterow(rational()))", null, null);
	}
	
	@Test
	public void testRationalLatex() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(1.2));
		args.add(OMCreator.createOMI(5));
		
		String result = func.getPartialLatexSyntax(args);
		
		assertEquals("\\frac{1.2}{5}", result);
	}
}
