package de.uni_due.s3.evaluator2.parser.antlr;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This is a RetryRule for Tests in the ParserPackage!
 * 
 * Using Random generated Strings, can result into generated Strings with
 * Command-Characters in it. Sometimes if a Command-Character is generated
 * 'poorly' on a bad spot, a Test may fail.
 * 
 * So this Class prevents these occasionally failed Tests and retries them
 * n-Times.
 * 
 * @author dlux
 *
 */
public class RetryRule implements TestRule {

	private int retryCount;

	public RetryRule(int retryCount) {
		this.retryCount = retryCount;
	}

	@Override
	public Statement apply(Statement base, Description description) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				Throwable caught = null;

				for (int i = 0; i < retryCount; i++) {
					try {
						base.evaluate();
						return;
					} catch (Throwable t) {
						System.err.println("There was an Error with a generated UTF-8 String at "
								+ description.getDisplayName() + ". Retrying");
						caught = t;
					}
				}
				throw caught;

			}

		};
	}

}