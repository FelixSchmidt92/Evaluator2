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

	//@Test
	public void testException1() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		R.evaluateInCAS("c(1,2,3,4)");
	}
}
