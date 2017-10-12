package de.uni_due.s3.evaluator2.core.integration.editor1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPaletteRow {

	@Test
	public void testPaletteRow() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("paletterow(plus())", null, null);
		List<Object>args = new ArrayList<Object>();
		args.add(OMSymbol.ARITH1_PLUS);
		assertEquals(OMCreator.createOMA(OMSymbol.EDITOR1_PALETTE_ROW, args),result.getOMA());
	}
	
	@Test(expected = FunctionNotImplementedException.class) 
	public void testPaletteRowWithNotImpelemtnedFunctions() throws JAXBException, EvaluatorException, OpenMathException {
		OMOBJ unkownFunction = OMConverter.toObject(""
				+ "<OMOBJ>"
				+ "<OMA>"
				+ "<OMS cd=\"editor1\" name=\"palette_row\"/> "
				+ "<OMA>"
				+ "<OMS cd=\"arith1\" name=\"unkownOperation\"/>"
				+ "</OMA>"	
				+ "</OMA>"
				+ "</OMOBJ>");
		Evaluator.evaluate(unkownFunction);
	}
}
