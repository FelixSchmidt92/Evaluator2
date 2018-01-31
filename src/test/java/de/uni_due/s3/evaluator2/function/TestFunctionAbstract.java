package de.uni_due.s3.evaluator2.function;

import org.junit.BeforeClass;

import de.uni_due.s3.evaluator2.r.TestR;
import de.uni_due.s3.evaluator2.sage.TestSage;

public abstract class TestFunctionAbstract {
	
	@BeforeClass
	public static void beforeClass() {
		TestSage.initSage();
		TestR.initR();
	}
}
