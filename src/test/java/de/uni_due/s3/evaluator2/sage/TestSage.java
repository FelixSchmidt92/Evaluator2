package de.uni_due.s3.evaluator2.sage;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.evaluator2.sage.SageConnection;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestSage {

	@BeforeClass
	public static void beforeClass() {
		initSage();
	}

	public static void initSage() {
		List<SageConnection> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add(new SageConnection("192.168.68.176", 8989));
		Sage.init(aSageConnectionsList);
	}

	@Test(expected = CasException.class)
	public void testException1() throws CasEvaluationException, CasNotAvailableException, OpenMathException {
		Sage.evaluateInCAS("");
	}
	
	@Test
	public void testMatrix() throws OpenMathException, EvaluatorException {
		OMOBJ result = Evaluator.evaluate("evaluateInSage('matrix(QQ,3,1,[1,2,3])')", null, null);
		System.out.println(result);
	}
}
