package de.uni_due.s3.evaluator2.r;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestR {

	@BeforeClass
	public static void beforeClass() {
		initR();
	}

	public static void initR() {
		List<RConn> rConnectionsList = new ArrayList<>();
		rConnectionsList.add(new RConn("192.168.68.207", 6312));
		R.init(rConnectionsList);
	}

	@Test
	public void testPI() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("pi");
	}
	
	@Test
	public void testNumber() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("5");
	}
	
	@Test
	public void testDouble() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("1.2345");
	}
	
	@Test
	public void testFunction() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("sqrt(4)");
	}
	
	@Test
	public void testString() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("\"abcdef\"");
	}
	
	@Test
	public void testLOGIC() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("FALSE");
	}
	
	@Test
	public void testList() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("list(1, 2.34, \"abcde\")");
	}
	
	@Test
	public void testMatrix() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("matrix(3,2,1)");
	}
	
	@Test
	public void testCombine() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("c(1,c(1,23),3)");
	}

	
}
