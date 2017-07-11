package de.uni_due.s3.evaluator.core.function.functions;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

import de.uni_due.s3.sage.Sage;

public abstract class TestFunction {

	@BeforeClass
	public static void beforeClass() {
		List<String> aSageConnectionsList = new ArrayList<>();
		aSageConnectionsList.add("192.168.68.176:8989");
		Sage.init(aSageConnectionsList);
	}
}
