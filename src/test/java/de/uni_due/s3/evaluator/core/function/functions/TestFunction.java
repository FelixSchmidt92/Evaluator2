package de.uni_due.s3.evaluator.core.function.functions;

import org.junit.BeforeClass;

import de.uni_due.s3.sage.TestSage;

public abstract class TestFunction {
	
	@BeforeClass
	public static void beforeClass() {
		TestSage.initSage();
	}
}
