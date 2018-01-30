package de.uni_due.s3.evaluator2.sage;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSage {

	@BeforeClass
	public static void beforeClass() {
		initSage();
	}

	public static void initSage() {
		List<SageConnection> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add(new SageConnection("192.168.68.73", 8989));
		Sage.init(aSageConnectionsList);
	}

	@Test(expected = CasException.class)
	public void testException1() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Sage.evaluateInCAS("");
	}
	
	@Test
	public void testEIsNotVariable() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		assertEquals(OMSymbol.LOGIC1_TRUE, Sage.evaluateInCAS("var('e'); e.is_real()"));
	}
	
	@Test
	public void testVariableE() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("var('e'); e = 5; e");
		
		assertEquals(OMCreator.createOMI(5), test);
	}
	
	@Test
	public void testConstantE() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("e");
		
		assertEquals(OMSymbol.NUMS1_E, test);
	}

	@Test
	public void testVariablePI() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("var('pi'); pi = 5; pi");
		
		assertEquals(OMCreator.createOMI(5), test);
	}
	
	@Test
	public void testConstantPI() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("pi");
		
		assertEquals(OMSymbol.NUMS1_PI, test);
	}
	
	@Test
	public void testVariableI() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("var('I'); I = 5; I");
		
		assertEquals(OMCreator.createOMI(5), test);
	}
	
	@Test
	public void testConstantI() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("I**2");
		
		assertEquals(OMCreator.createOMI(-1), test);
	}
	
	@Test
	public void testVariable_i() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("var('i'); i = 5; i");
		
		assertEquals(OMCreator.createOMI(5), test);
	}
	
	@Test
	public void testConstant_i() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Object test = Sage.evaluateInCAS("i**2");
		
		assertEquals(OMCreator.createOMI(-1), test);
	}
}
