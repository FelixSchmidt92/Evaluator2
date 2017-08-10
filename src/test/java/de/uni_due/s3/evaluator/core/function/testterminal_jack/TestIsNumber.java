package de.uni_due.s3.evaluator.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.testterminal_jack.IsNumber;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsNumber extends TestFunctionAbstract{
	
	Function func = new IsNumber();
	
	@Test
	public void testIsNumberOMIandOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
	public void testIsNumberOMSTR() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("string");
		args.add(omstr);
		
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}
	
	@Test
	public void testIsNumberOMV() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMV omstr = OMCreator.createOMV("x");
		args.add(omstr);
		
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}
	
	@Test
	public void testIsNumberOMA() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, new ArrayList<>());
		args.add(oma);
		
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}
	
	@Test
	public void testIsNumberOMS() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMS oms = OMSymbol.NUMS1_PI;
		args.add(oms);
		
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
		
		oms = OMSymbol.NUMS1_E;
		args.remove(0);
		args.add(oms);
		
		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));
		
		oms = OMSymbol.ARITH1_ABS;
		args.remove(0);
		args.add(oms);
		
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}
}
