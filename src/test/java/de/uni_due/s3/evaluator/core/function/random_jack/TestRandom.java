package de.uni_due.s3.evaluator.core.function.random_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.random_jack.Random;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
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
	public void testRandom() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {

		for (int i = 0; i < 10000; i++) {
			OMF omf = (OMF) func.evaluate(new ArrayList<>());
			assertTrue(1 > omf.getDec());
			assertTrue(0 <= omf.getDec());
		}
	}

	@Test
	public void testRandomStatistically() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
	public void testRandomCaseIntegrationEmptyArguments() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ p = ExpressionParser.parse("random()", null, null); // Test for
																	// Empty
																	// Arguments!
		OMExecutor.execute(p);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomIntegerationWithMoreArguments() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ p = ExpressionParser.parse("random(2)", null, null); // Test for
																	// Empty
																	// Arguments!
		OMExecutor.execute(p);
		fail();
	}

}
