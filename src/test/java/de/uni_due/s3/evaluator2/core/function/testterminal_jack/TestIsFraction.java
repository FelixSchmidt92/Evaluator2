package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsFraction extends TestFunctionAbstract {

	Function func = new isFraction();

	@Test
	public void testIsFractionWithRational() throws OpenMathException, EvaluatorException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMF(1.1));

		OMA oma = OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, omel);
		ArrayList<Object> args = new ArrayList<>();

		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsFractionWithDivide() throws OpenMathException, EvaluatorException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMF(1.1));

		OMA oma = OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel);
		ArrayList<Object> args = new ArrayList<>();

		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsFractionWithOMI() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMI(2));

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsFractionWithOMF() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(2.1));

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsFractionWithOMV() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMV("x"));

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsFractionWithOMSTR() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("string"));

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsFractionCaseIntegration() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isFraction(3/4)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMExecutor.execute(omobj).getOMS());
	}
}