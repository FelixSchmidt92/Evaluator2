package de.uni_due.s3.evaluator2.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.testterminal_jack.IsFraction;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsFraction extends TestFunctionAbstract {

	Function func = new IsFraction();

	@Test
	public void testIsFractionWithRational() throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMF(1.1));

		OMA oma = OMCreator.createOMA(OMSymbol.NUMS1_RATIONAL, omel);
		ArrayList<Object> args = new ArrayList<>();

		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsFractionWithDivide() throws EvaluatorException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMF(1.1));

		OMA oma = OMCreator.createOMA(OMSymbol.ARITH1_DIVIDE, omel);
		ArrayList<Object> args = new ArrayList<>();

		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsFractionWithOMI() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMI(2));

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsFractionWithOMF() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(2.1));

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsFractionWithOMV() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMV("x"));

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testIsFractionWithOMSTR() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("string"));

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsFractionCaseIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isFraction(3/4)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
}
