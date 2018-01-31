package de.uni_due.s3.evaluator2.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.testterminal_jack.IsNaturalNumber;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsNaturalNumber extends TestFunctionAbstract {

	Function func = new IsNaturalNumber();

	@Test
	public void testIsNaturalNumberOMI() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(1);
		args.add(omi);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsNaturalNumberOMF() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(1.1);
		args.add(omf);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsNaturalNumberOMSTR() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("string");
		args.add(omstr);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsNaturalNumberOMV() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMV omstr = OMCreator.createOMV("x");
		args.add(omstr);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsNaturalNumberOMA() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> args2 = new ArrayList<>();
		args2.add(OMCreator.createOMF(1));

		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args2);
		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsNaturalNumberOMS1() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMS oms = OMSymbol.NUMS1_PI;
		args.add(oms);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsNaturalNumberOMS2() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMS oms = OMSymbol.NUMS1_E;
		args.add(oms);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsNaturalNumberCaseIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isNaturalNumber(3)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void testIsNaturalNumberCaseIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isNaturalNumber(3/3)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void testIsNaturalNumberCaseIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isNaturalNumber(abs(3))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void testIsNaturalNumberCaseIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isNaturalNumber(-3)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}
}
