package de.uni_due.s3.sage;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.cashandler.Sage;
import de.uni_due.s3.evaluator.exceptions.CASEvaluationException;

public class TestSage {

	@BeforeClass
	public static void beforeClass(){
		List<String> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add("192.168.68.176:8989");
		Sage.init(aSageConnectionsList);
	}
	
	@Test
	public void test() throws CASEvaluationException {
		Object result = Sage.evaluateInCAS("matrix(QQ,1,2,[3,4])");
		System.out.println(result.toString());
	}
}
