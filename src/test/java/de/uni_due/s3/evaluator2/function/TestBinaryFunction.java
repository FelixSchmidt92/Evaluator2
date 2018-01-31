package de.uni_due.s3.evaluator2.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestBinaryFunction {

	BinaryFunction bfunc = new BinaryFunction() {
		
		@Override
		protected int minArgs() {
			return 123;
		}
		
		@Override
		protected int maxArgs() {
			return 321;
		}
		
		@Override
		protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
			return null;
		}
	};
	
	
	@Test(expected = NoRepresentationAvailableException.class)
	public void testGetBinaryLatexOMA() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMIOMF(1));
		args.add(OMCreator.createOMIOMF(2));
		
		OMA oma = OMCreator.createOMA(OMSymbol.ARITH1_PLUS, args);
		bfunc.getBinaryLatex(oma);	
	}
	
	@Test
	public void testGetBinaryLatexOMS() throws EvaluatorException, OpenMathException {
		String result = bfunc.getBinaryLatex(OMSymbol.NUMS1_NAN);
		assertEquals("NaN", result);
	}
	
	@Test
	public void testGetBinaryLatexOMI() throws EvaluatorException, OpenMathException {
		OMI omi = OMCreator.createOMI(1);
		String result = bfunc.getBinaryLatex(omi);
		assertEquals("1", result);
	}
	
	@Test
	public void testGetBinaryLatexOMSTR() throws EvaluatorException, OpenMathException {
		OMSTR omstr = OMCreator.createOMSTR("test");
		String result = bfunc.getBinaryLatex(omstr);
		assertEquals("test", result);
	}
	
	@Test
	public void testGetBinaryLatexOMF() throws EvaluatorException, OpenMathException {
		OMF omf = OMCreator.createOMF(1.234);
		String result = bfunc.getBinaryLatex(omf);
		assertEquals("1.234", result);
	}
	
	@Test
	public void testGetBinaryLatexOMV() throws EvaluatorException, OpenMathException {
		OMV omv = OMCreator.createOMV("a");
		String result = bfunc.getBinaryLatex(omv);
		assertEquals("a", result);
	}
}
