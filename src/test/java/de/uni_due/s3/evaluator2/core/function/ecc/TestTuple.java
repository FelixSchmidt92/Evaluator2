package de.uni_due.s3.evaluator2.core.function.ecc;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestTuple extends TestFunctionAbstract{

	Function func = new Tuple();
	
	@Test
	public void testTupleIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMSTR("str"));
		expected.setOMA(OMCreator.createOMA(OMSymbol.ECC_TUPLE, omel));
		
		OMOBJ actual = Evaluator.evaluate("tuple(1, 'str')", null, null);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTupleIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMI(3));
		expected.setOMA(OMCreator.createOMA(OMSymbol.ECC_TUPLE, omel));
		
		OMOBJ actual = Evaluator.evaluate("tuple(1,2,3)", null, null);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTupleEvaluateInSage() throws EvaluatorException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMI(OMCreator.createOMI(2));
		
		
		HashMap<String, OMOBJ> vars = new HashMap<>();
		vars.put("a", Evaluator.evaluate("tuple(a,2,3)", null, null));
		OMOBJ actual = Evaluator.evaluate("evaluateInSage('[var=a][1]')", vars, null);
		assertEquals(expected, actual);
	}
	
	@Test(expected = NoRepresentationAvailableException.class)
	public void testTupleBoolean() throws EvaluatorException, OpenMathException {
		func.getPartialBooleanSyntax(new ArrayList<>());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testTupleDouble() throws EvaluatorException, OpenMathException {
		assertEquals(new Double(Math.E), func.getPartialDoubleSyntax(new ArrayList<>()), 0);
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testTupleInteger() throws EvaluatorException, OpenMathException {
		func.getPartialIntegerSyntax(new ArrayList<>());
	}

	@Test
	public void testTupleLatex() throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMI(3));
		assertEquals("(1, 2, 3)", func.getPartialLatexSyntax(omel));
	}

	@Test
	public void testTupleSage() throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMV("a"));
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMI(3));
		assertEquals("tuple([a, 2, 3])", func.getPartialSageSyntax(omel));
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testTupleString() throws EvaluatorException, OpenMathException {
		func.getPartialStringSyntax(new ArrayList<>());
	}

	@Test
	public void testTupleR() throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMI(3));
		assertEquals("tuple(1, 2, 3, TRUE)", func.getPartialRSyntax(omel));
	}
	
	@Test
	public void testTupleList() throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMI(3));
		OMA expectedOMA = OMCreator.createOMA(OMSymbol.ECC_TUPLE, omel);
		ArrayList<Object> expected = new ArrayList<>();
		expected.add(expectedOMA);
		
		assertEquals(expected, func.getPartialListSyntax(omel));
	}
}
