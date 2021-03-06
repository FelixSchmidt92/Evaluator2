package de.uni_due.s3.evaluator2.function.random_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.random_jack.Random;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This test is copied from the old Evaluator. True Randomness is not testable,
 * but this test tests that at least 10 000 Numbers from Random.class are not
 * the same and between 0 and 1.
 * 
 * @author dlux
 *
 */
public class TestRandom extends TestFunctionAbstract {

	Function func = new Random();

	@Test
	public void testRandom() throws EvaluatorException, OpenMathException {

		for (int i = 0; i < 10000; i++) {
			OMF omf = (OMF) func.evaluate(new ArrayList<>());
			assertTrue(1 > omf.getDec());
			assertTrue(0 <= omf.getDec());
		}
	}

	@Test
	public void testRandomStatistically() throws EvaluatorException, OpenMathException {
		HashMap<Double, Integer> statistics = new HashMap<>(); // <'Random
																// Number',
																// 'Occurrences'>
		for (int i = 0; i < 10000; i++) {
			OMF omf = (OMF) func.evaluate(new ArrayList<>());
			double random = omf.getDec();
			if (statistics.containsKey(random)) {
				statistics.put(random, statistics.get(random) + 1);
			} else {
				statistics.put(random, 1);
			}
		}

		int max = 0;
		for (int value : statistics.values()) {
			if (value > max) {
				max = value;
			}
		}

		assertTrue(2 >= max); // max 2 same random numbers out of 10 000
		assertTrue(9990 < statistics.values().size()); // max 9 random numbers
														// overlapping out of 10
														// 000
	}

	@Test
	public void testRandomCaseIntegrationEmptyArguments() throws EvaluatorException, OpenMathException {
		OMOBJ p = ExpressionParser.parse("random()", null, null); // Test for
																	// Empty
																	// Arguments!
		OMToResultVisitor.getInstance().visit(p);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomIntegerationWithMoreArguments() throws EvaluatorException, OpenMathException {
		OMOBJ p = ExpressionParser.parse("random(2)", null, null); // Test for
																	// Empty
																	// Arguments!
		OMToResultVisitor.getInstance().visit(p);
		fail();
	}

}
