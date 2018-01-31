package de.uni_due.s3.evaluator2.function.random_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.random_jack.RandomIntegerBetween;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
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
	public void testRandomBetween() throws EvaluatorException, OpenMathException {

		for (int i = 0; i < 10000; i++) {
			OMI omi = (OMI) func.evaluate(args);
			assertTrue(Integer.parseInt(omi.getValue()) >= min);
			assertTrue(Integer.parseInt(omi.getValue()) <= max);
		}
	}

	@Test
	public void testRandomBetweenIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ p = ExpressionParser.parse("randomBetween(3,10)", null, null);

		double result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(p)).getOMF().getDec();
		assertTrue(3 <= result && result < 10);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomBetweenIntegerationTooFewArguments() throws EvaluatorException, OpenMathException {
		OMOBJ p = ExpressionParser.parse("randomBetween()", null, null);
		OMToResultVisitor.getInstance().visit(p);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testRandomBetweenIntegerationWithWrongArguments() throws EvaluatorException, OpenMathException {
		OMOBJ p = ExpressionParser.parse("randomBetween('','')", null, null);
		OMToResultVisitor.getInstance().visit(p);
		fail();
	}
}
