package de.uni_due.s3.evaluator2.sage;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
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
}
