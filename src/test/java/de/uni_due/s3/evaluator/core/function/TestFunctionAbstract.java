package de.uni_due.s3.evaluator.core.function;

import org.junit.BeforeClass;

import de.uni_due.s3.sage.TestSage;

public abstract class TestFunctionAbstract {
	
	@BeforeClass
	public static void beforeClass() {
		TestSage.initSage();
	}
}