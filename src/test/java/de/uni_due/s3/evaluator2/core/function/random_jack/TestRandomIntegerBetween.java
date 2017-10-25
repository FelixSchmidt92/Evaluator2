package de.uni_due.s3.evaluator2.core.function.random_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomIntegerBetween {

	Function func = new RandomIntegerBetween();
	private final static float min = 3;
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
			OMI omi = (OMI) func.evaluate(args);
			assertTrue(Integer.parseInt(omi.getValue()) >= min);
			assertTrue(Integer.parseInt(omi.getValue()) <= max);
		}
	}

	@Test
	public void testRandomBetweenIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ p = ExpressionParser.parse("randomBetween(3,10)", null, null);

		double result = new OMToResultVisitor().execute(p).getOMF().getDec();
		assertTrue(3 <= result && result < 10);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomBetweenIntegerationTooFewArguments() throws OpenMathException, EvaluatorException {
		OMOBJ p = ExpressionParser.parse("randomBetween()", null, null);
		new OMToResultVisitor().execute(p);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testRandomBetweenIntegerationWithWrongArguments() throws OpenMathException, EvaluatorException {
		OMOBJ p = ExpressionParser.parse("randomBetween('','')", null, null);
		new OMToResultVisitor().execute(p);
		fail();
	}
}
