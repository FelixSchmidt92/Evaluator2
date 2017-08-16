package de.uni_due.s3.evaluator2.core.function.random_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This test is copied from the old Evaluator. True Randomness is not tested,
 * just the limits.
 * 
 * @author frichtscheid
 *
 */
public class TestRandomBetween extends TestFunctionAbstract {

	Function func = new RandomBetween();
	private final static float min = 3.6f;
	private final static int max = 200;
	private final static List<Object> args = new ArrayList<Object>();

	@BeforeClass
	public static void init() {
		args.add(OMCreator.createOMF(min));
		args.add(OMCreator.createOMI(max));
	}

	@Test
	public void testRandomBetween() throws OpenMathException, EvaluatorException {

		for (int i = 0; i < 10000; i++) {
			OMF omf = (OMF) func.evaluate(args);
			assertTrue(omf.getDec() >= min);
			assertTrue(omf.getDec() < max);
		}
	}

	@Test
	public void testRandomBetweenIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ p = ExpressionParser.parse("randombetween(3,10)", null, null);

		double result = OMExecutor.execute(p).getOMF().getDec();
		assertTrue(3 <= result && result < 10);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomBetweenIntegerationTooFewArguments() throws OpenMathException, EvaluatorException {
		OMOBJ p = ExpressionParser.parse("randombetween()", null, null);
		OMExecutor.execute(p);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testRandomBetweenIntegerationWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ p = ExpressionParser.parse("randombetween('','')", null, null);
		OMExecutor.execute(p);
		fail();
	}

}
