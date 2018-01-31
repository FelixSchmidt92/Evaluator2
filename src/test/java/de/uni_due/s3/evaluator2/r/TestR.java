package de.uni_due.s3.evaluator2.r;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestR {

	@BeforeClass
	public static void beforeClass() {
		initR();
	}

	public static void initR() {
		List<RConn> rConnectionsList = new ArrayList<>();
		rConnectionsList.add(new RConn("192.168.68.73", 6351));
		R.init(rConnectionsList);
	}

	@Test
	public void testPI() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMF actual = (OMF) R.evaluateInCAS("pi");
		assertEquals(Math.PI, actual.getDec(), 0);
	}
	
	@Test
	public void testNumber() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMI actual = (OMI) R.evaluateInCAS("5");
		OMI expected = OMCreator.createOMI(5);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDouble() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMF actual = (OMF) R.evaluateInCAS("1.2345");
		OMF expected = OMCreator.createOMF(1.2345);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFunction() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMI actual = (OMI) R.evaluateInCAS("sqrt(4)");
		OMI expected = OMCreator.createOMI(2);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testString() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMSTR actual = (OMSTR) R.evaluateInCAS("\"abcdef\"");
		OMSTR expected = OMCreator.createOMSTR("abcdef");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLOGICFalse() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMS actual = (OMS) R.evaluateInCAS("FALSE");
		assertEquals(OMSymbol.LOGIC1_FALSE, actual);
	}
	
	@Test
	public void testLOGICTrue() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMS actual = (OMS) R.evaluateInCAS("TRUE");
		assertEquals(OMSymbol.LOGIC1_TRUE, actual);
	}
	
	@Test
	public void testList() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMA actual = (OMA) R.evaluateInCAS("list(1, 2.34, \"abcde\")");
		OMI val1 = OMCreator.createOMI(1);
		OMF val2 = OMCreator.createOMF(2.34);
		OMSTR val3 = OMCreator.createOMSTR("abcde");
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(val1);
		args.add(val2);
		args.add(val3);
		OMA expected = OMCreator.createOMA(OMSymbol.LIST1_LIST, args);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMatrix() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMA actual = (OMA) R.evaluateInCAS("matrix(c(1,2,3,4,5,6),2,3)");
		OMI val1 = OMCreator.createOMI(1);
		OMI val2 = OMCreator.createOMI(2);
		OMI val3 = OMCreator.createOMI(3);
		OMI val4 = OMCreator.createOMI(4);
		OMI val5 = OMCreator.createOMI(5);
		OMI val6 = OMCreator.createOMI(6);
		
		ArrayList<Object> args1 = new ArrayList<>();
		args1.add(val1);
		args1.add(val3);
		args1.add(val5);
		ArrayList<Object> args2 = new ArrayList<>();
		args2.add(val2);
		args2.add(val4);
		args2.add(val6);
		
		OMA row1 = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args1);
		OMA row2 = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args2);
		
		ArrayList<Object> elements = new ArrayList<>();
		elements.add(row1);
		elements.add(row2);
		
		OMA expected = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, elements);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCombineWithInCombine() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMA actual = (OMA) R.evaluateInCAS("c(1,c(1,2.3),3)");
		
		OMI val1 = OMCreator.createOMI(1);
		OMF val2 = OMCreator.createOMF(2.3);
		OMI val3 = OMCreator.createOMI(3);
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(val1);
		args.add(val1);
		args.add(val2);
		args.add(val3);
		OMA expected = OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCombine() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		OMA actual = (OMA) R.evaluateInCAS("c(1, 2, 3.3)");
		
		OMI val1 = OMCreator.createOMI(1);
		OMI val2 = OMCreator.createOMI(2);
		OMF val3 = OMCreator.createOMF(3.3);
		
		ArrayList<Object> args = new ArrayList<>();
		args.add(val1);
		args.add(val2);
		args.add(val3);
		OMA expected = OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args);
		assertEquals(expected, actual);
		
	}
	
	@Test(expected=CasEvaluationException.class)
	public void testError() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("c");
	}

	
}
