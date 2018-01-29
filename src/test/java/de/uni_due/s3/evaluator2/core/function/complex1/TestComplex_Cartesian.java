package de.uni_due.s3.evaluator2.core.function.complex1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestComplex_Cartesian extends TestFunctionAbstract {

	private Function func = new Complex_Cartesian();

	@Test
	public void testComplex1() throws EvaluatorException, OpenMathException {
		OMOBJ result = ExpressionParser.parse("complex(1,2)", null, null);
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		Object oma = func.evaluate(args);
		assertEquals(result.getOMA(), oma);
	}

	@Test
	public void testComplex2() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("evaluateInSage('2+I*5')", null, null);
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(5));
		Object oma = func.evaluate(args);
		assertEquals(result.getOMA(), oma);
	}

	@Test
	public void testComplex3() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("evaluateInSage('(3*i+7)*(2+I*5)')", null, null);
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(-1));
		args.add(OMCreator.createOMI(41));
		Object oma = func.evaluate(args);
		assertEquals(result.getOMA(), oma);
	}

	@Test
	public void testComplex4() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("evaluateInSage('(3*i+15)/3-i')", null, null);
		OMOBJ omi = OMCreator.createOMOBJ(OMCreator.createOMI(5));
		assertEquals(omi, result);
	}
}
