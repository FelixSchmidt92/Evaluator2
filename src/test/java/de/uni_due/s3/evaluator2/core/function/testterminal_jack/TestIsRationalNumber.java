package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsRationalNumber extends TestFunctionAbstract {

	Function func = new IsRationalNumber();

	@Test
	public void testIsRationalNumberOMIandOMF() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(1);
		args.add(omi);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));

		OMF omf = OMCreator.createOMF(1.1);
		args.remove(0);
		args.add(omf);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
	}

	@Test
	public void testIsRationalNumberOMSTR() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("string");
		args.add(omstr);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsRationalNumberOMV() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMV omstr = OMCreator.createOMV("x");
		args.add(omstr);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsRationalNumberOMA() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> args2 = new ArrayList<>();
		args2.add(OMCreator.createOMF(1));
		
		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args2);
		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsRationalNumberOMS() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMS oms = OMSymbol.NUMS1_PI;
		args.add(oms);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));

		oms = OMSymbol.NUMS1_E;
		args.remove(0);
		args.add(oms);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}
	
	@Test
	public void testIsRationalNumberCaseIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isRationalNumber(3/4)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMExecutor.execute(omobj).getOMS());
	}
	
	@Test
	public void testIsRationalNumberCaseIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isRationalNumber(sqrt(2))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMExecutor.execute(omobj).getOMS());
	}
	
	@Test
	public void testIsRationalNumberCaseIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isRationalNumber(sqrt(-1))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMExecutor.execute(omobj).getOMS());
	}
}
