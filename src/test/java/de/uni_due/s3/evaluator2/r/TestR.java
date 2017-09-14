package de.uni_due.s3.evaluator2.r;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.evaluator2.sage.SageConnection;

public class TestR {

	@BeforeClass
	public static void beforeClass() {
		initSage();
	}

	public static void initSage() {
		List<SageConnection> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add(new SageConnection("192.168.68.127", 6311));
		Sage.init(aSageConnectionsList);
	}

//	@Test(expected = CasException.class)
//	public void testException1() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
//		Sage.evaluateInCAS("");
//	}
}
