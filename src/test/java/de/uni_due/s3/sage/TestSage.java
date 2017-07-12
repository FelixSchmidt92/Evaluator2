package de.uni_due.s3.sage;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestSage {

	@BeforeClass
	public static void beforeClass(){
		initSage();
	}
	
	public static void initSage() {
		List<String> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add("192.168.68.176:8989");
		Sage.init(aSageConnectionsList);
	}

	@Test
	public void test() {
		try {
			Object result = Sage.evaluateInCAS("");
			System.out.println(result.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
