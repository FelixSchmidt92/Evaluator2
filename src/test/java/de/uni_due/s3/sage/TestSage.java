package de.uni_due.s3.sage;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSage {

	@BeforeClass
	public static void beforeClass() {
		initSage();
	}

	public static void initSage() {
		List<String> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add("192.168.68.176:8989");
		Sage.init(aSageConnectionsList);
	}

	@Test(expected = OpenMathException.class)
	public void test() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Sage.evaluateInCAS("");
	}
}
