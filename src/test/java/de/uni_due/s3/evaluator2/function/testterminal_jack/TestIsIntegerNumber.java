package de.uni_due.s3.evaluator2.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.testterminal_jack.IsIntegerNumber;
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

public class TestIsIntegerNumber extends TestFunctionAbstract {

	Function func = new IsIntegerNumber();

	@Test
	public void testIsIntegerNumberOMI() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(1);
		args.add(omi);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsIntegerNumberOMF() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(1.1);
		args.add(omf);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsIntegerNumberOMSTR() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("string");
		args.add(omstr);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsIntegerNumberOMV() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMV omstr = OMCreator.createOMV("x");
		args.add(omstr);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsIntegerNumberOMA() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> args2 = new ArrayList<>();
		args2.add(OMCreator.createOMF(1));

		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, args2);
		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsIntegerNumberOMS1() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMS oms = OMSymbol.NUMS1_PI;
		args.add(oms);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsIntegerNumberOMS2() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMS oms = OMSymbol.NUMS1_E;
		args.add(oms);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsIntegerNumberCaseIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isIntegerNumber(3)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void testIsIntegerNumberCaseIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isIntegerNumber(3/3)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void testIsIntegerNumberCaseIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isIntegerNumber(abs(3))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void testIsIntegerNumberCaseIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isIntegerNumber(0)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void testIsIntegerNumberCaseIntegration5() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isIntegerNumber(-102891)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
}
